package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree;

import java.util.Set;

import org.jgrapht.Graph;

import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.edgeFinder.NextEdgeFinder;
import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.vertexFinder.VertexFinder;
import lennart.magnus.borchert.graphFramework.materials.FlexibleGraph;

/**
 * 
 * 
 * @author Lenno
 *
 * @param <V> the used Vertex class
 * @param <E> the used Edge class
 */
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
	
	/**
	 * Adds the given Edge and one of its incident vertices, which wasn't part of the spanning tree before, to the spanning tree.
	 * 
	 * @param edge which to add to the spanning tree.
	 */
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
