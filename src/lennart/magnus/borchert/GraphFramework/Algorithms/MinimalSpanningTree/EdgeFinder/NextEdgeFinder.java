package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.Set;

public interface NextEdgeFinder<V, E> {
	public E getNextEdge(Set<V> spanningTreeVertexSet);
	public void addEdgesToPriorityQueue(V vertex);
}
