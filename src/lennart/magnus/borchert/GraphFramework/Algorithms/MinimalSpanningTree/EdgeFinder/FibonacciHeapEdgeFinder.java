package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.Set;

import org.jgrapht.Graph;

public class FibonacciHeapEdgeFinder<V, E> extends AbstractNextEdgeFinder<V, E> implements NextEdgeFinder<V, E>{


	public FibonacciHeapEdgeFinder(Graph<V, E> graph) {
		super(graph);
	}

	@Override
	protected void createPriorityQueue(Graph<V, E> graph) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E getNextEdge(Set<V> spanningTreeVertexSet) {
		// TODO Auto-generated method stub
		return null;
	}

}
