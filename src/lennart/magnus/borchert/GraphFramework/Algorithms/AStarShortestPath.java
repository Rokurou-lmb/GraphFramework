package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public class AStarShortestPath<V, E> implements ShortestPathAlgorithm<V, E>{
	//TODO: implement A* Algorithm
	private AStarShortestPath<V, E> _instance;
	
	private AStarShortestPath(){
		
	};
	
	public AStarShortestPath<V, E> getInstance(){
		if (_instance == null) {
			_instance = new AStarShortestPath<V, E>();
		}
		return _instance;
	}

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}
}
