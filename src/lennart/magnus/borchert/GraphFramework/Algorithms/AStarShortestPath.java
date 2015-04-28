package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public class AStarShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{
	//TODO: implement A* Algorithm
	
	private AStarShortestPath(){
		
	};
	
	public AStarShortestPath<V, E> getInstance(){
		return (AStarShortestPath<V, E>)super.getInstance();
	}

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractShortestPathAlgorithm<V, E> InstanceExists() {
		if (super._instance == null) {
			super._instance = new AStarShortestPath<>();
		}
		return super._instance;
	}

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex,
			V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}
}
