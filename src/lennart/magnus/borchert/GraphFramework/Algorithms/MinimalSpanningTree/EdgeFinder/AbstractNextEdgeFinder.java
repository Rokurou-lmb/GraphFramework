package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

public abstract class AbstractNextEdgeFinder<V, E> implements NextEdgeFinder<V, E>{
	protected Graph<V, E> _graph;
	protected List<E> _processedEdges;

	public AbstractNextEdgeFinder(Graph<V, E> graph){
		createPriorityQueue(graph);
		_graph = graph;
	}

	protected void createPriorityQueue(Graph<V, E> graph){
		_graph = graph;
		_processedEdges = new ArrayList<E>();
	}

	abstract protected void addEntryToPriorityQueue(E edge);

	public void addEdgesToPriorityQueue(V vertex){
		Set<E> edgeSet = _graph.edgesOf(vertex);
		for (E edge : edgeSet) {
			if(!_processedEdges.contains(edge))
				addEntryToPriorityQueue(edge);
		}
	}
}
