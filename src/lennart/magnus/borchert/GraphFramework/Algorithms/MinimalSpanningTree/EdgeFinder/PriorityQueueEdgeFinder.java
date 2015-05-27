package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.EdgeComparator;

import org.jgrapht.Graph;

public class PriorityQueueEdgeFinder<V, E> extends AbstractNextEdgeFinder<V, E> {
	
	public PriorityQueueEdgeFinder(Graph<V, E> graph) {
		super(graph);
	}

	private PriorityQueue<E> _priorityQueue;

	@Override
	protected void createPriorityQueue(Graph<V, E> graph) {
		super.createPriorityQueue(graph);
		EdgeComparator<V, E> comparator = new EdgeComparator<>(graph);
		_priorityQueue = new PriorityQueue<>(comparator);
	}

	@Override
	public E getNextEdge(Set<V> spanningTreeVertexSet) {
		E nextEdge;
		V sourceVertex;
		V targetVertex;
		Set<V> edgeVertices = new HashSet<>();
		do{
			nextEdge = _priorityQueue.poll();
			sourceVertex = _graph.getEdgeSource(nextEdge);
			targetVertex = _graph.getEdgeTarget(nextEdge);
			edgeVertices.clear();
			edgeVertices.add(sourceVertex);
			edgeVertices.add(targetVertex);
		}while(_priorityQueue.size() > 0 && (sourceVertex.equals(targetVertex) || spanningTreeVertexSet.containsAll(edgeVertices)));
		//Solange die Liste nicht leer ist && (die Kante eine Schleife ist || beide Knoten bereits im Spannbaum sind)
		_processedEdges.add(nextEdge);
		return nextEdge;
	}

	@Override
	protected void addEntryToPriorityQueue(E edge) {
		_priorityQueue.add(edge);
	}
}
