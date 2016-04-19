package lennart.magnus.borchert.graphFramework.algorithms.eulerTour;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.GraphPathImpl;

import lennart.magnus.borchert.graphFramework.algorithms.shortestPath.BreadthFirstSearchShortestPath;
import lennart.magnus.borchert.graphFramework.algorithms.shortestPath.ShortestPathAlgorithm;
import lennart.magnus.borchert.graphFramework.materials.FlexibleGraph;

public class FleuryEulerTour<V, E> implements EulerTourAlgorithm<V, E>{
	private Class<? extends E> _edgeClass;
	
	public FleuryEulerTour(Class<? extends E> edgeClass){
		_edgeClass = edgeClass;
	}

	@Override
	public GraphPath<V, E> findEulerCircle(Graph<V,E> graph) {

		Graph<V, E> workingGraph = new FlexibleGraph<>((FlexibleGraph<V,E>)graph, _edgeClass);

		V w0 = graph.vertexSet().iterator().next();
		List<E> marked = new ArrayList<>();

		ShortestPathAlgorithm<V, E> bfs = new BreadthFirstSearchShortestPath<>();

		while (marked.size() < graph.edgeSet().size()){
			//get an edge candidate
			E candidate = getEdgeCandidate(workingGraph,w0,bfs);
			if(candidate != null && graph.edgesOf(w0).size()%2==0){
				marked.add(candidate);
				if (w0.equals(graph.getEdgeTarget(candidate))) {
					w0 = graph.getEdgeSource(candidate);
				}else if (w0.equals(graph.getEdgeSource(candidate))){
					w0 = graph.getEdgeTarget(candidate);
				}
			}else {
				return new GraphPathImpl<>(graph,w0,w0,new ArrayList<>(),0);
			}
		}
		return new GraphPathImpl<>(graph,w0,w0,marked,0);
	}

	/**
	 *
	 * @param graph the graph that contains all remaining edges
	 * @param w0 the vertex we are working on
	 * @param spa the algorithm we are using to test for bridge
	 * @return <code>Edge</code> if there is one <code>null</code> if there is none
	 */
	private E getEdgeCandidate(Graph<V,E> graph,V w0, ShortestPathAlgorithm<V, E> spa){
		Iterator<E> edgeIterator = graph.edgesOf(w0).iterator();
		V candidateS;
		V candidateT;
		E candidate = null;
		while (edgeIterator.hasNext()){
			candidate = edgeIterator.next();
			candidateS = graph.getEdgeSource(candidate);
			candidateT = graph.getEdgeTarget(candidate);
			graph.removeEdge(candidate);
			if(spa.findShortestPath(graph,candidateS,candidateT).size()==0){
				return candidate;
			}else graph.addEdge(candidateS, candidateT, candidate);
		}
		return candidate;
	}

}
