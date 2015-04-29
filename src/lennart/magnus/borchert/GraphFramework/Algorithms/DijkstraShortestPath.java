package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;

public class DijkstraShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		//TODO: implement Dijkstra Algorithm
		List<V> path = new ArrayList<V>();
		Map<V, DijkstraDataTable<V>> dataTable = new HashMap<>();
		DijkstraDataTable<V> tableEntry = new DijkstraDataTable<>();
		tableEntry.setDistance(0);
		dataTable.put(startVertex, tableEntry);
		for (V vertex : graph.vertexSet()) {
			dataTable.put(vertex, new DijkstraDataTable<>());
		}
		//TODO: the algorithm implementation, using DataTable created beforehand
		return path;
	}
	
	private class DijkstraDataTable<V>{
		private int _distance;
		private V _predecessor;
		private boolean _ok;
		
		public DijkstraDataTable(){
			_distance = Integer.MAX_VALUE;
			_predecessor = null;
			_ok = false;
		}

		/**
		 * @return the _distance
		 */
		public int getDistance() {
			return _distance;
		}

		/**
		 * @return the _predecessor
		 */
		public V getPredecessor() {
			return _predecessor;
		}

		/**
		 * @return the _ok
		 */
		public boolean getOk() {
			return _ok;
		}

		/**
		 * @param _distance the _distance to set
		 */
		public void setDistance(int _distance) {
			this._distance = _distance;
		}

		/**
		 * @param _predecessor the _predecessor to set
		 */
		public void setPredecessor(V _predecessor) {
			this._predecessor = _predecessor;
		}

		/**
		 * @param _ok the _ok to set
		 */
		public void setOk(boolean _ok) {
			this._ok = _ok;
		}

		/**
		 * 
		 * @param distance
		 * @param predecessor
		 */
		public void setDistanceAndPredecessor(int distance, V predecessor){
			_distance = distance;
			_predecessor = predecessor;
		}
	}
}
