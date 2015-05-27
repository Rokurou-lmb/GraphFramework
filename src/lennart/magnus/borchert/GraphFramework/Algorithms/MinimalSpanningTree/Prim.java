package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder.NextEdgeFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder.VertexFinder;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;

import org.jgrapht.Graph;

public class Prim<V, E> implements MinimalSpanningTreeAlgorithm<V, E>{
	
	private NextEdgeFinder<V, E> _edgeFinder;
	private VertexFinder<V, E> _vertexFinder;
	private Graph<V, E> _graph;

	public Prim(NextEdgeFinder<V, E> edgeFinder, VertexFinder<V, E> vertexFinder) {
		_edgeFinder = edgeFinder;
		_vertexFinder = vertexFinder;
	}

	@Override
	public Graph<V, E> createMinimalSpanningTree(Graph<V, E> graph, Class<E> edgeClass) {
		_graph = graph;
		Set<V> graphVertexSet = _graph.vertexSet();
		Graph<V, E> spanningTree = new FlexibleGraph<>(false, true, edgeClass);
		V firstVertex = _vertexFinder.getVertex();
		spanningTree.addVertex(firstVertex);
		while(!spanningTree.vertexSet().containsAll(graphVertexSet)){
			E edge = _edgeFinder.getNextEdge(spanningTree.vertexSet());
			V sourceVertex = _graph.getEdgeSource(edge);
			V targetVertex = _graph.getEdgeTarget(edge);
			spanningTree.addVertex(sourceVertex);
			spanningTree.addVertex(targetVertex);
			spanningTree.addEdge(sourceVertex, targetVertex, edge);
		}
		return spanningTree;
	}
	
	
}
