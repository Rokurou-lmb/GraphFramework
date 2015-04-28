package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public class DijkstraShortestPath<V, E> implements ShortestPathAlgorithm<V, E>{
	//TODO: implement Dijkstra Algorithm
	private DijkstraShortestPath<V, E> _instance;
	
	private DijkstraShortestPath(){
		
	};
	
	public DijkstraShortestPath<V, E> getInstance(){
		if (_instance == null) {
			_instance = new DijkstraShortestPath<>();
		}
		return _instance;
	}

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}
}
