package lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;

import org.jgrapht.Graph;

public class DijkstraShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{
	private Graph<V, E> _graph;
	private V _startVertex;
	private V _endVertex;
	private Map<V, DijkstraDataTableEntry> _dataTable;
	private Set<V> _openSet;
	private Set<V> _closedSet;
	private List<V> _path;


	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		_graph = graph;
		_startVertex = startVertex;
		_endVertex = endVertex;
		_dataTable = createDijkstraDataTable();
		_openSet = new HashSet<>();
		_openSet.add(startVertex);
		_closedSet = new HashSet<>();
		_path = new ArrayList<V>();

		V currentVertex = _startVertex;
		boolean foundShortestPath = false;

		while(!_openSet.isEmpty() && !foundShortestPath){
			_openSet.remove(currentVertex);
			_closedSet.add(currentVertex);

			Set<E> outgoingEdges = ((FlexibleGraph<V, E>) graph).getOutgoingEdges(currentVertex);
			for (E edge : outgoingEdges) {
				processVertex(currentVertex, edge);
			}

			currentVertex = getNextVertex();
			foundShortestPath = _closedSet.contains(endVertex);
		}

		if(foundShortestPath)
			_path = traceTakenPath();

		return _path;
	}

	/**
	 * Creates a DijkstraDataTable with given input
	 * 
	 * @param graph Graph on which the Dijkstra is executed
	 * @param startVertex Vertex from which the Dijkstra starts searching
	 * @return A dataTable which can be used in the overlying Dijkstra implementation
	 */
	private Map<V, DijkstraDataTableEntry> createDijkstraDataTable(){
		Map<V, DijkstraDataTableEntry> dataTable = new HashMap<>();

		DijkstraDataTableEntry firstTableEntry = new DijkstraDataTableEntry();
		firstTableEntry.setDistanceAndPredecessor(0, _startVertex);
		dataTable.put(_startVertex, firstTableEntry);
		Set<V> vertexSet = _graph.vertexSet();
		vertexSet.remove(_startVertex);
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
	private V getNextVertex(){
		// TODO make this safe
		V nextVertex = null;
		double minDistance = Double.POSITIVE_INFINITY;
		Iterator<V> openSetIterator = _openSet.iterator();

		while(openSetIterator.hasNext()){
			V currentVertex = openSetIterator.next();
			double currentVertexDistance = _dataTable.get(currentVertex).getDistance();
			if(currentVertexDistance < minDistance){
				minDistance = currentVertexDistance;
				nextVertex = currentVertex;
			}
		}
		return nextVertex;
	}
	
	private void processVertex(V currentVertex, E edge){
		//TODO maybe isn't undirected safe
		DijkstraDataTableEntry currentVertexData = _dataTable.get(currentVertex);
		V targetVertex = _graph.getEdgeTarget(edge);
		DijkstraDataTableEntry targetVertexData = _dataTable.get(targetVertex);

		if(!_closedSet.contains(targetVertex)){// If shortest path to currentVertex was not found yet
			_openSet.add(targetVertex);
			double distanceSum = currentVertexData.getDistance() + _graph.getEdgeWeight(edge);
			if(distanceSum < targetVertexData.getDistance()){
				targetVertexData.setDistanceAndPredecessor(distanceSum, currentVertex);
			}
		}
	}

	/**
	 * Traces the shortest path back from the endVertex to the startVertex, using the filled dataTable, 
	 * created by the DijkstraShortestPath implementation.
	 * 
	 * @param dataTable of Dijkstra instance that is to be tested
	 * @param endVertex Vertex which the shortest path was requested for
	 * @return Shortest path from startVertex to endVertex
	 */
	private List<V> traceTakenPath(){
		LinkedList<V> path = new LinkedList<>();
		V currentVertex = _endVertex;
		DijkstraDataTableEntry currentEntry = _dataTable.get(currentVertex);
		while(currentEntry.getDistance() != 0 && !Double.isInfinite(currentEntry.getDistance())){
			path.addFirst(currentVertex);
			currentVertex = _dataTable.get(currentVertex).getPredecessor();
			currentEntry = _dataTable.get(currentVertex);
		}
		path.addFirst(currentVertex);
		return path;
	}
	
	
	private class DijkstraDataTableEntry{
		private double _distance;
		private V _predecessor;

		public DijkstraDataTableEntry(){
			_distance = Double.POSITIVE_INFINITY;
			_predecessor = null;
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
