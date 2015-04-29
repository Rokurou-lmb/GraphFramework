package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;

import org.jgrapht.Graph;

public class DijkstraShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		List<V> path = new ArrayList<V>();
		Map<V, DijkstraDataTableEntry> dataTable = createDijkstraDataTable(graph, startVertex);

		V currentVertex = startVertex;
		V targetVertex;
		boolean foundShortestPath = false;
		DijkstraDataTableEntry endVertexData = dataTable.get(endVertex);

		boolean doSearch = true;
		while(doSearch && !foundShortestPath){
			doSearch = false;

			dataTable.get(currentVertex).setOk(true);
			Set<E> outgoingEdges = ((FlexibleGraph<V, E>) graph).getOutgoingEdges(currentVertex);
			for (E edge : outgoingEdges) {
				targetVertex = graph.getEdgeTarget(edge);
				processVertex(graph, dataTable,currentVertex, targetVertex, edge);
			}

			currentVertex = getNextVertex(dataTable);
			doSearch = isDijkstraFinished(dataTable);
			foundShortestPath = endVertexData.getOk();
		}

		if(foundShortestPath)
			path = traceTakenPath(dataTable, endVertex);

		return path;
	}

	/**
	 * Creates a DijkstraDataTable with given input
	 * 
	 * @param graph Graph on which the Dijkstra is executed
	 * @param startVertex Vertex from which the Dijkstra starts searching
	 * @return A dataTable which can be used in the overlying Dijkstra implementation
	 */
	private Map<V, DijkstraDataTableEntry> createDijkstraDataTable(Graph<V, E> graph, V startVertex){
		Map<V, DijkstraDataTableEntry> dataTable = new HashMap<>();

		DijkstraDataTableEntry firstTableEntry = new DijkstraDataTableEntry();
		firstTableEntry.setDistance(0);
		dataTable.put(startVertex, firstTableEntry);
		Set<V> vertexSet = graph.vertexSet();
		for (V vertex : vertexSet) {
			dataTable.put(vertex, new DijkstraDataTableEntry());
		}

		return dataTable;
	}

	/**
	 * Returns the next Vertex to be used in the DijkstraShortestPath implementation
	 * 
	 * @param dataTable
	 * @return Vertex to be used next
	 */
	private V getNextVertex(Map<V, DijkstraDataTableEntry> dataTable){
		V nextVertex = null;
		double minDistance = Double.MAX_VALUE;
		Set<Map.Entry<V, DijkstraDataTableEntry>> dataTableEntries = dataTable.entrySet();
		Iterator<Map.Entry<V, DijkstraDataTableEntry>> dataTableEntryIterator = dataTableEntries.iterator();

		Map.Entry<V, DijkstraDataTableEntry> currentEntry;
		while(dataTableEntryIterator.hasNext()){
			currentEntry = dataTableEntryIterator.next();
			double currentEntryDistance = currentEntry.getValue().getDistance();
			if(currentEntryDistance < minDistance){
				minDistance = currentEntryDistance;
				nextVertex = currentEntry.getKey();
			}
		}
		return nextVertex;
	}
	
	private void processVertex(Graph<V, E> graph, Map<V, DijkstraDataTableEntry> dataTable,V currentVertex, V targetVertex, E edge){

		DijkstraDataTableEntry currentVertexData = dataTable.get(currentVertex);
		DijkstraDataTableEntry targetVertexData = dataTable.get(targetVertex);
		double distanceSum;
		
		targetVertex = graph.getEdgeTarget(edge);

		if(!dataTable.get(targetVertex).getOk()){// If shortest path to currentVertex was not found yet
			distanceSum = currentVertexData.getDistance()+graph.getEdgeWeight(edge);
			if(distanceSum < targetVertexData.getDistance()){
				targetVertexData.setDistanceAndPredecessor(distanceSum, currentVertex);
			}
		}
	}

	/**
	 * Checks whether or not the given DijkstraDataTable is finished or still needs to be worked on.
	 * 
	 * @param dataTable of Dijkstra instance that is to be tested
	 * @return true if Dijkstra is finished, false if not. To be exact, returns false if there is an entry within the dataTable 
	 * 		   with an defined predeccesor that is also not Ok
	 */
	private boolean isDijkstraFinished(Map<V, DijkstraDataTableEntry> dataTable){
		Set<Map.Entry<V, DijkstraDataTableEntry>> dataTableSet = dataTable.entrySet();
		Iterator<Map.Entry<V, DijkstraDataTableEntry>> dataTableSetIterator = dataTableSet.iterator();
		boolean isFinished = true;

		while(dataTableSetIterator.hasNext() && isFinished){
			Map.Entry<V, DijkstraDataTableEntry> dataTableEntry = dataTableSetIterator.next();
			
			isFinished = !(dataTableEntry.getValue().getOk() && dataTableEntry.getValue().getPredecessor().equals(null));
			//If there is an entry with an defined predeccesor that is also not Ok, Dijkstra is not finished
			}
		return isFinished;
	}

	/**
	 * Traces the shortest path back from the endVertex to the startVertex, using the filled dataTable, 
	 * created by the DijkstraShortestPath implementation.
	 * 
	 * @param dataTable of Dijkstra instance that is to be tested
	 * @param endVertex Vertex which the shortest path was requested for
	 * @return Shortest path from startVertex to endVertex
	 */
	private List<V> traceTakenPath(Map<V, DijkstraDataTableEntry> dataTable, V endVertex){
		LinkedList<V> path = new LinkedList<>();
		V currentVertex = endVertex;
		while(dataTable.get(currentVertex).getDistance() != 0){
			path.add(currentVertex);
			currentVertex = dataTable.get(currentVertex).getPredecessor();
		}
		return path;
	}
	
	
	private class DijkstraDataTableEntry{
		private double _distance;
		private V _predecessor;
		private boolean _ok;

		public DijkstraDataTableEntry(){
			_distance = Integer.MAX_VALUE;
			_predecessor = null;
			_ok = false;
		}

		/**
		 * @return the _distance
		 */
		public double getDistance() {
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
		public void setDistance(double _distance) {
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
		public void setDistanceAndPredecessor(double distance, V predecessor){
			_distance = distance;
			_predecessor = predecessor;
		}
	}
}
