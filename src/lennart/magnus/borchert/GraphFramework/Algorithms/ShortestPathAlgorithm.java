package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public interface ShortestPathAlgorithm<V, E> {

	/**
	 * This method will return the shortest path from startVertex to endVertex in graph, if one exists.
	 * 
	 * @param Graph in which to search for shortest path.
	 * @param startVertex
	 * @param endVertex
	 * @return List<V> which contains all V on the shortest path from startVertex to endVertex 
	 * 		   or an empty List if no path was found.
	 */
	public List<V> findShortestPath(Graph<V, E> graph,V startVertex,V endVertex);
	
}
