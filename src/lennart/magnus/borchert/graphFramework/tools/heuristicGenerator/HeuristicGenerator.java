package lennart.magnus.borchert.graphFramework.tools.heuristicGenerator;

import org.jgrapht.Graph;

public interface HeuristicGenerator<V, E> {

	/**
	 * Adds calculated Heuristicdata to the given Graph.
	 * 
	 * @param graph
	 * @param endVertex
	 */
	public void calculateHeuristic(Graph<V, E> graph, V endVertex);


}
