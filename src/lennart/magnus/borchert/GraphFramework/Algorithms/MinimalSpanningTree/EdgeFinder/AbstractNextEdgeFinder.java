package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder;

import org.jgrapht.Graph;

public abstract class AbstractNextEdgeFinder<V, E> implements NextEdgeFinder<V, E>{
	
	public AbstractNextEdgeFinder(Graph<V, E> graph){
		createPriorityQueue(graph);
	}
	
	abstract protected void createPriorityQueue(Graph<V, E> graph);
}
