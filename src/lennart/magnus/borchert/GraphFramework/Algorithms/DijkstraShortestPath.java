package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		Map<V, DijkstraDataTable<V>> dataTable = new HashMap<>();
		DijkstraDataTable<V> tableEntry = new DijkstraDataTable<>();
		tableEntry._distance = 0;
		dataTable.put(startVertex, tableEntry);
		for (V vertex : graph.vertexSet()) {
			dataTable.put(vertex, new DijkstraDataTable<>());
		}
		//TODO: the algorithm implementation, using DataTable created beforehand
		return path;
	}
	
	private class DijkstraDataTable<V>{
		public int _distance;
		public V _predecessor;
		public boolean _ok;
		
		public DijkstraDataTable(){
			_distance = Integer.MAX_VALUE;
			_predecessor = null;
			_ok = false;
		}
		
		public void setDAndP(int i, V v){
			_distance = i;
			_predecessor = v;
		}
	}
}
