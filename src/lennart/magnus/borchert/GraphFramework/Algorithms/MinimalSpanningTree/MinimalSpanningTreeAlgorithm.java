package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import org.jgrapht.Graph;

/**
 * A MinimalSpanningTree Algorithm which generates a minimal spanning tree for a given connected weighted Graph.
 * 
 * @author Lenno
 *
 * @param <V> the used Vertex class
 * @param <E> the used Edge class
 */
public interface MinimalSpanningTreeAlgorithm<V, E> {

	/**
	 * Creates a minimal spanning tree for the given graph and returns it.
	 * 
	 * @param graph a connected weighted Graph
	 * @param edgeClass the Class of edges used within graph
	 * @return A Graph<V, E> containing a minimal spanning tree for the given graph.
	 */
	public Graph<V, E> createMinimalSpanningTree(Graph<V, E> graph, Class<E> edgeClass);
}
