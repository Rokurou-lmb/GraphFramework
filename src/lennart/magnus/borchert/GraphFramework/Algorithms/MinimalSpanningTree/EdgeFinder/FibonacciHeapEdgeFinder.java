package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

public class FibonacciHeapEdgeFinder<V, E> extends AbstractNextEdgeFinder<V, E> implements NextEdgeFinder<V, E>{

	private FibonacciHeap<E> _fibonacciHeap;
	private Graph<V, E> _graph;

	public FibonacciHeapEdgeFinder(Graph<V, E> graph) {
		super(graph);
	}

	@Override
	protected void createPriorityQueue(Graph<V, E> graph) {
		_fibonacciHeap = new FibonacciHeap();
		_graph = graph;
		
		Set<E> edgeSet = graph.edgeSet();
		for (E edge : edgeSet) {
			_fibonacciHeap.insert(new FibonacciHeapNode<E>(edge), graph.getEdgeWeight(edge));
		}
		
	}

	@Override
	public E getNextEdge(Set<V> spanningTreeVertexSet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEdgesToPriorityQueue(V vertex) {
		// TODO Auto-generated method stub
		
	}
}
