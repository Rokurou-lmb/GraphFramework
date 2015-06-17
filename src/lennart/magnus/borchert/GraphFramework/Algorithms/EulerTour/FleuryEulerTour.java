package lennart.magnus.borchert.GraphFramework.Algorithms.EulerTour;

import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.BreadthFirstSearchShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.ShortestPathAlgorithm;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.graph.GraphPathImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FleuryEulerTour<V, E> implements EulerTourAlgorithm<V, E>{

	@Override
	public GraphPathImpl<V, E> findEulerCircle(Graph graph) {

		Graph<Vertex,Edge> _graph = graph;
		Graph<Vertex,Edge> eulerGraph = new FlexibleGraph<>(((FlexibleGraph)graph).isDirected(),((FlexibleGraph)graph).isWeighted(),Edge.class);
		for(Edge e : _graph.edgeSet()){
			Vertex s = _graph.getEdgeSource(e);
			eulerGraph.addVertex(s);
			Vertex t = _graph.getEdgeTarget(e);
			eulerGraph.addVertex(t);
			eulerGraph.addEdge(s,t, e);
		}

		Vertex w0 = (Vertex) graph.vertexSet().iterator().next();
		List<E> marked = new LinkedList<>();

		ShortestPathAlgorithm bfs = new BreadthFirstSearchShortestPath<>();

		while (marked.size() < graph.edgeSet().size()){
			//get an edge candidate
			Edge candidate = getEdgeCandidate(eulerGraph,w0,bfs);
			if(candidate != null && ((FlexibleGraph<Vertex,Edge>) _graph).getOutgoingEdges(w0).size()%2==0){
				marked.add((E)candidate);
				if (w0.getIdentifier().equals((_graph.getEdgeTarget(candidate)).getIdentifier())) {
					w0 = _graph.getEdgeSource(candidate);
				}else if (w0.getIdentifier().equals((_graph.getEdgeSource(candidate)).getIdentifier())){
					w0 = _graph.getEdgeTarget(candidate);
				}
			}else {
				return new GraphPathImpl<V,E>(graph,(V)w0,(V)w0,new ArrayList<>(),0);
			}
		}
		return new GraphPathImpl<V,E>(graph,(V)w0,(V)w0,marked,0);
	}

	/**
	 *
	 * @param graph the graph that contains all remaining edges
	 * @param w0 the vertex we are working on
	 * @param spa the algorithm we are using to test for bridge
	 * @return <code>Edge</code> if there is one <code>null</code> if there is none
	 */
	private Edge getEdgeCandidate(Graph<Vertex,Edge> graph,Vertex w0, ShortestPathAlgorithm spa){
		Iterator<Edge> edgeIterator = ((FlexibleGraph<Vertex,Edge>) graph).getOutgoingEdges(w0).iterator();
		Vertex candidateS;
		Vertex candidateT;
		Edge candidate = null;
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
