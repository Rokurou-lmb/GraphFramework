package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.EdgeComparator;

import org.jgrapht.Graph;

public class PriorityQueueEdgeFinder<V, E> extends AbstractNextEdgeFinder<V, E> {
	
	public PriorityQueueEdgeFinder(Graph<V, E> graph) {
		super(graph);
	}

	private PriorityQueue<E> _priorityQueue;
	private Graph<V, E> _graph;

	@Override
	protected void createPriorityQueue(Graph<V, E> graph) {
		EdgeComparator<V, E> comparator = new EdgeComparator<>(graph);
		_priorityQueue = new PriorityQueue<>(comparator);
		_graph = graph;

		Set<E> edgeSet = graph.edgeSet();
		for (E edge : edgeSet) {
			_priorityQueue.add(edge);
		}
	}

	@Override
	public E getNextEdge(Set<V> spanningTreeVertexSet) {
		E nextEdge = null;
		@SuppressWarnings("unchecked")
		E[] edgeArray = (E[])_priorityQueue.toArray();
		EdgeComparator<V, E> edgeComparator = new EdgeComparator<>(_graph);
		Arrays.sort(edgeArray, edgeComparator);
		boolean foundEdge = false;
		for(int i = 0; i < edgeArray.length && !foundEdge; i++){
			E currentEdge = edgeArray[i];
			if(spanningTreeVertexSet.contains(_graph.getEdgeSource(currentEdge))){
				if(spanningTreeVertexSet.contains(_graph.getEdgeTarget(currentEdge))){
					_priorityQueue.remove(currentEdge);
				} else {
					nextEdge = currentEdge;
					_priorityQueue.remove(currentEdge);
					foundEdge = true;
				}
			} else if(spanningTreeVertexSet.contains(_graph.getEdgeTarget(currentEdge))){
				if(spanningTreeVertexSet.contains(_graph.getEdgeSource(currentEdge))){
					_priorityQueue.remove(currentEdge);
				} else {
					nextEdge = currentEdge;
					_priorityQueue.remove(currentEdge);
					foundEdge = true;
				}
			}
		}
		return nextEdge;
	}
}
