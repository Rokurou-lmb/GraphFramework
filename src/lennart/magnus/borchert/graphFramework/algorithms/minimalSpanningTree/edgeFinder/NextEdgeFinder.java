package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.edgeFinder;

import java.util.Set;

/**
 * 
 * @author Lenno
 *
 * @param <V> the used Vertex class
 * @param <E> the used Edge class
 */
public interface NextEdgeFinder<V, E> {
	/**
	 * Gives the next edge to be added to the spanning tree, according to the given vertexSet.
	 * 
	 * @param spanningTreeVertexSet 
	 * @return The next Edge which to add to the spanning tree
	 */
	public E getNextEdge(Set<V> spanningTreeVertexSet);

	/**
	 * Adds all new edges incident to the given vertex to the underlying Structure.
	 * Has to be called everytime after a vertex was added to the spanning tree, and before the next Edge is given.
	 * 
	 * @param vertex which has last been added to the spanning tree.
	 */
	public void addEdgesToPriorityQueue(V vertex);
}
