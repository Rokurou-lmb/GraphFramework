package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public class AStarShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{

	private AStarShortestPath(){}

	@Override
	protected AbstractShortestPathAlgorithm<V, E> InstanceExists() {
		if (super._instance == null) {
			super._instance = new AStarShortestPath<>();
		}
		return super._instance;
	}

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		//TODO: implement A* Algorithm
		return null;
	}
}