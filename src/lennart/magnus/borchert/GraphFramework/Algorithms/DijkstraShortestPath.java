package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

public class DijkstraShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{

	private DijkstraShortestPath(){}

	@Override
	protected AbstractShortestPathAlgorithm<V, E> InstanceExists() {
		if (super._instance == null) {
			super._instance = new DijkstraShortestPath<>();
		}
		return super._instance;
	}

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		//TODO: implement Dijkstra Algorithm
		List<V> path = new ArrayList<V>();
		
		return path;
	}
	
	private class DijkstraDataTable<V>{
		public int _distance;
		public V _predecessor;
		public boolean _ok;
	}
}
