package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.ArrayList;
import java.util.List;
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
	private List<E> _processedEdges;

	@Override
	protected void createPriorityQueue(Graph<V, E> graph) {
		EdgeComparator<V, E> comparator = new EdgeComparator<>(graph);
		_priorityQueue = new PriorityQueue<>(comparator);
		_graph = graph;
		_processedEdges = new ArrayList<E>();
	}

	public void addEdgesToPriorityQueue(V vertex){
		_priorityQueue.addAll(_graph.edgesOf(vertex));
	}

	@Override
	public E getNextEdge(Set<V> spanningTreeVertexSet) {
		E nextEdge = _priorityQueue.poll();
		while(_priorityQueue.size() > 0  &&
				(_processedEdges.contains(nextEdge) || _graph.getEdgeSource(nextEdge).equals(_graph.getEdgeTarget(nextEdge)))){

				nextEdge = _priorityQueue.poll();
		}
		_processedEdges.add(nextEdge);
		return nextEdge;
	}
}
