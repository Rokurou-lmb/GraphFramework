package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;


public class BreadthFirstSearch<V, E> implements ShortestPathAlgorithm<V, E>{

	private BreadthFirstSearch<V, E> _instance;
	
	private BreadthFirstSearch(){
		
	};
	
	public BreadthFirstSearch<V, E> getInstance(){
		if (_instance == null) {
			_instance = new BreadthFirstSearch<>();
		}
		return _instance;
	}

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}
}
