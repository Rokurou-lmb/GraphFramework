package lennart.magnus.borchert.GraphFramework.Algorithms.EulerTour;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;

/**
 * 
 * @author Lenno
 *
 * @param <V> the used Vertex class
 * @param <E> the used Edge class
 */
public interface EulerTourAlgorithm<V, E> {

	/**
	 * 
	 * @return
	 */
	public GraphPath<V, E> findEulerCircle(Graph<V, E> graph);
}
