package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import org.jgrapht.Graph;

public interface MinimalSpanningTreeAlgorithm<V, E> {

	public Graph<V, E> createMinimalSpanningTree(Graph<V, E> graph, Class<E> edgeClass);
}
