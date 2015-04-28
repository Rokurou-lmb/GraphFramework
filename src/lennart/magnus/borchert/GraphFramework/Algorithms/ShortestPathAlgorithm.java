package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public interface ShortestPathAlgorithm<V, E> {
	
	public ShortestPathAlgorithm<V, E> getInstance();

	public List<V> findShortestPath(Graph<V, E> graph,V startVertex,V endVertex);
	
}
