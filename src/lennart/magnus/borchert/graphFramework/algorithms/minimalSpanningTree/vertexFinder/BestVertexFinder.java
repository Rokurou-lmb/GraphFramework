package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.vertexFinder;

import java.util.Queue;

import org.jgrapht.Graph;

public class BestVertexFinder<V, E> extends AbstractVertexFinder<V, E> {
	private Queue<E> _priorityQueue;
	
	public BestVertexFinder(Graph<V, E> graph, Queue<E> priorityQueue){
		super(graph);
		_priorityQueue = priorityQueue;
	}

	@Override
	public V getVertex() {
		E firstEdge = _priorityQueue.poll();
		return _graph.getEdgeSource(firstEdge);
	}

}
