package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder.NextEdgeFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder.VertexFinder;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;

import org.jgrapht.Graph;

public class Prim<V, E> implements MinimalSpanningTreeAlgorithm<V, E>{
	
	private NextEdgeFinder<V, E> _edgeFinder;
	private VertexFinder<V, E> _vertexFinder;
	Graph<V, E> _spanningTree;
	private Graph<V, E> _graph;

	/**
	 * Initializes this object with the given Tools
	 * 
	 * @param edgeFinder an edgeFinder tool to use.
	 * @param vertexFinder an vertexFinder tool to use.
	 */
	public Prim(NextEdgeFinder<V, E> edgeFinder, VertexFinder<V, E> vertexFinder) {
		_edgeFinder = edgeFinder;
		_vertexFinder = vertexFinder;
	}

	@Override
	public Graph<V, E> createMinimalSpanningTree(Graph<V, E> graph, Class<E> edgeClass) {
		_spanningTree = new FlexibleGraph<>(false, true, edgeClass);
		_graph = graph;
		V firstVertex = _vertexFinder.getVertex();
		_spanningTree.addVertex(firstVertex);
		_edgeFinder.addEdgesToPriorityQueue(firstVertex);
		while(!_spanningTree.vertexSet().containsAll(_graph.vertexSet())){
			E edge = _edgeFinder.getNextEdge(_spanningTree.vertexSet());
			addEdgeToTree(edge);
		}
		return _spanningTree;
	}
	
	private void addEdgeToTree(E edge){
		Set<V> spanningTreeVertexSet = _spanningTree.vertexSet();
		V sourceVertex = _graph.getEdgeSource(edge);
		V targetVertex = _graph.getEdgeTarget(edge);
		if(spanningTreeVertexSet.contains(sourceVertex)){
			_spanningTree.addVertex(targetVertex);
			_spanningTree.addEdge(sourceVertex, targetVertex, edge);
			_edgeFinder.addEdgesToPriorityQueue(targetVertex);
		} else {
			_spanningTree.addVertex(sourceVertex);
			_spanningTree.addEdge(sourceVertex, targetVertex, edge);
			_edgeFinder.addEdgesToPriorityQueue(sourceVertex);
		}
	}
	
	
}
