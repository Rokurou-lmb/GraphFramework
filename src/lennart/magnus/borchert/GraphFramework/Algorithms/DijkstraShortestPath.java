package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;

public class DijkstraShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{
	//TODO: implement Dijkstra Algorithm
	
	private DijkstraShortestPath(){
		
	};
	
	public DijkstraShortestPath<V, E> getInstance(){
		return (DijkstraShortestPath<V, E>)super.getInstance();
	}

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractShortestPathAlgorithm<V, E> InstanceExists() {
		if (super._instance == null) {
			super._instance = new DijkstraShortestPath<>();
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
