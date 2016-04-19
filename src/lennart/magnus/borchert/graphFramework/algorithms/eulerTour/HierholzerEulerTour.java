package lennart.magnus.borchert.graphFramework.algorithms.eulerTour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

public class HierholzerEulerTour<V, E> implements EulerTourAlgorithm<V, E> {

	private Graph<V, E> _graph;
	private Random _rng;
	private V _startVertex;
	private Set<E> _surpressedEdges;
	private Map<V, List<GraphPath<V, E>>> _startVertexToSubCircleMapping;
	private List<E> _eulerTourEdgeList;
	private double _eulerTourWeight;

	@Override
	public GraphPath<V, E> findEulerCircle(Graph<V, E> graph) {
		initilizeDataStructure(graph);
		_startVertex = getNextSubCircleStartVertex();
		V currentSubCircleStartVertex = _startVertex;

		while(_surpressedEdges.size() < graph.edgeSet().size()){ //As long as there are unprocessed edges
			if(!_startVertexToSubCircleMapping.containsKey(currentSubCircleStartVertex))
				_startVertexToSubCircleMapping.put(currentSubCircleStartVertex, new ArrayList<>());

			GraphPathImpl<V, E> currectSubCircle = buildSubCircle(currentSubCircleStartVertex);

			List<GraphPath<V, E>> subCircleList = _startVertexToSubCircleMapping.get(currentSubCircleStartVertex);
			subCircleList.add(currectSubCircle);
			_startVertexToSubCircleMapping.put(currentSubCircleStartVertex, subCircleList);
			currentSubCircleStartVertex = getNextSubCircleStartVertex();
		}
		return resolveSubCircles();
	}

	private GraphPathImpl<V, E> buildSubCircle(V subCircleStartVertex){
		List<E> currentSubCircleEdgeList = new ArrayList<>();
		V currentVertex = subCircleStartVertex;
		E nextSubCircleEdge = null;
		double weight = 0.0;
		do{
			nextSubCircleEdge = getRandomNextEdgeForSubCircle(currentVertex);

			currentSubCircleEdgeList.add(nextSubCircleEdge);
			_surpressedEdges.add(nextSubCircleEdge);
			weight += _graph.getEdgeWeight(nextSubCircleEdge);

			currentVertex = getOtherVertexTouchedByEdge(nextSubCircleEdge, currentVertex);

		}while(!subCircleStartVertex.equals(currentVertex));
		return new GraphPathImpl<V, E>(_graph, subCircleStartVertex, currentVertex, currentSubCircleEdgeList, weight);
	}

	private V getNextSubCircleStartVertex(){ //TODO: Refactor
		List<E> unprocessedEdges = getUnprocessedEdges(null);
		V result = null;
		if(unprocessedEdges.size() > 0){
			E randomUnprocessedEdge = unprocessedEdges.get(_rng.nextInt(unprocessedEdges.size()));
			result = _graph.getEdgeSource(randomUnprocessedEdge); //It doesn't matter if you choose source or target in an undirected graph but it does in a directed graph
		}
		return result;
	}

	private E getRandomNextEdgeForSubCircle(V currentSubCircleVertex){
		List<E> unprocessedEdges = getUnprocessedEdges(currentSubCircleVertex);
		return unprocessedEdges.get(_rng.nextInt(unprocessedEdges.size()));
	}

	private GraphPathImpl<V, E> resolveSubCircles(){
		resolveSubCircle(_startVertex, _eulerTourEdgeList, _eulerTourWeight);
		GraphPathImpl<V, E> eulerTour = new GraphPathImpl<V, E>(_graph, _startVertex, _startVertex, _eulerTourEdgeList, _eulerTourWeight);
		return eulerTour;
	}

	private void resolveSubCircle(V startVertex, List<E> eulerTourEdgeList, double weight){
		List<GraphPath<V, E>> subCircleList = _startVertexToSubCircleMapping.get(startVertex);
		GraphPath<V, E> currentSubCirclePath = subCircleList.remove(0);
		if(subCircleList.size() == 0) //If this was the last subCircle starting at this vertex, remove the entry
			_startVertexToSubCircleMapping.remove(startVertex);
		
		List<E> currentSubCircleEdgeList = currentSubCirclePath.getEdgeList();
		Iterator<E> currentSubCircleEdgeListIterator = currentSubCircleEdgeList.iterator();
		V currentVertex = startVertex;
		do{
			if(_startVertexToSubCircleMapping.containsKey(currentVertex))
				resolveSubCircle(currentVertex, eulerTourEdgeList, weight);
			E currentEdge = currentSubCircleEdgeListIterator.next();
			eulerTourEdgeList.add(currentEdge);
			currentVertex = getOtherVertexTouchedByEdge(currentEdge, currentVertex);

		}while(!startVertex.equals(currentVertex));
	}

	/**
	 * Returns all unprocessed edges for the given vertex, or all unprocessed edges in the graph, if the given vertex is null
	 * @param vertex Vertex to be processed or null if instead all unprocessed edges are wanted
	 * @return
	 */
	private List<E> getUnprocessedEdges(V vertex){
		Set<E> edgeSet = (vertex == null) ? _graph.edgeSet() : _graph.edgesOf(vertex);
		Set<E> modifiableSet = new LinkedHashSet<>();
		modifiableSet.addAll(edgeSet);
		modifiableSet.removeAll(_surpressedEdges);
		return getEdgeListFromEdgeSet(modifiableSet);
	}

	private List<E> getEdgeListFromEdgeSet(Set<E> set){
		List<E> list = new ArrayList<>();
		list.addAll(set);
		return list;
	}

	private V getOtherVertexTouchedByEdge(E edge, V vertex){
		return (_graph.getEdgeTarget(edge).equals(vertex)) ? _graph.getEdgeSource(edge) : _graph.getEdgeTarget(edge);
	}

	private void initilizeDataStructure(Graph<V, E> graph){
		_graph = graph;
		_rng = new Random();
		_surpressedEdges = new LinkedHashSet<>();
		_startVertexToSubCircleMapping = new HashMap<V, List<GraphPath<V,E>>>();
		_eulerTourEdgeList = new ArrayList<>();
		_eulerTourWeight = 0.0;
	}
}
