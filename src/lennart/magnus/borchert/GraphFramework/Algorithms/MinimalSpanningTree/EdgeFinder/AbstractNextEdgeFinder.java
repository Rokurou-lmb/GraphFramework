package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;

/**
 * @author Lenno
 *
 * @param <V> the used Vertex class
 * @param <E> the used Edge class
 */
public abstract class AbstractNextEdgeFinder<V, E> implements NextEdgeFinder<V, E>{
	protected Graph<V, E> _graph;
	protected List<E> _processedEdges;

	/**
	 * Initializes this object with data from the given graph.
	 * 
	 * @param graph A connected weighted Graph.
	 */
	public AbstractNextEdgeFinder(Graph<V, E> graph){
		_graph = graph;
		createPriorityQueue();
	}

	/**
	 * Initializes the underlying structure.
	 */
	protected void createPriorityQueue(){
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
