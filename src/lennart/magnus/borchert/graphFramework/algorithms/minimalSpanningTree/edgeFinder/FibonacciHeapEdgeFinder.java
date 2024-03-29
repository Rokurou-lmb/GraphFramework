package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.edgeFinder;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

/**
 * This implementation makes use of org.jgrapht.util.FibonacciHeap
 * 
 * @author Lenno
 *
 * @param <V> the used Vertex class
 * @param <E> the used Edge class
 */
public class FibonacciHeapEdgeFinder<V, E> extends AbstractNextEdgeFinder<V, E> implements NextEdgeFinder<V, E>{

	private FibonacciHeap<E> _fibonacciHeap;

	public FibonacciHeapEdgeFinder(Graph<V, E> graph) {
		super(graph);
	}

	@Override
	protected void createPriorityQueue() {
		super.createPriorityQueue();
		_fibonacciHeap = new FibonacciHeap<>();
	}

	@Override
	public E getNextEdge(Set<V> spanningTreeVertexSet) {
		E nextEdge;
		V sourceVertex;
		V targetVertex;
		Set<V> edgeVertices = new HashSet<>();
		do{
			nextEdge = _fibonacciHeap.removeMin().getData();
			sourceVertex = _graph.getEdgeSource(nextEdge);
			targetVertex = _graph.getEdgeTarget(nextEdge);
			edgeVertices.clear();
			edgeVertices.add(sourceVertex);
			edgeVertices.add(targetVertex);
		}while(_fibonacciHeap.size() > 0 && (sourceVertex.equals(targetVertex) || spanningTreeVertexSet.containsAll(edgeVertices)));
		//Solange die Liste nicht leer ist && (die Kante eine Schleife ist || beide Knoten bereits im Spannbaum sind)
		_processedEdges.add(nextEdge);
		return nextEdge;
	}

	@Override
	protected void addEntryToPriorityQueue(E edge) {
		_fibonacciHeap.insert(new FibonacciHeapNode<E>(edge), _graph.getEdgeWeight(edge));
	}
}
