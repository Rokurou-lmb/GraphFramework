package lennart.magnus.borchert.GraphFramework.Tools.HeuristicGenerators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.jgrapht.Graph;

public class DijkstraPerfectHeuristicGenerator<V extends Vertex, E> implements HeuristicGenerator<V, E>{
	Graph<V, E> _graph;
	Set<V> _openSet;
	Set<V> _closedSet;
	Map<V, DijkstraDataTableEntry> _dataTable;


	public void calculateHeuristic(Graph<V, E> graph, V endVertex) {
		_graph = graph;
		_openSet = new HashSet<>();
		_closedSet = new HashSet<>();
		_openSet.add(endVertex);
		createDijkstraDataTable(endVertex);

		V currentVertex = endVertex;

		boolean continueCalculating = true;
		while(continueCalculating){
			continueCalculating = false;

			_openSet.remove(currentVertex);
			_closedSet.add(currentVertex);
			Set<E> outgoingEdges = ((FlexibleGraph<V, E>) graph).getOutgoingEdges(currentVertex);
			for (E edge : outgoingEdges) {
				V targetVertex = graph.getEdgeTarget(edge);
				processVertex( currentVertex, targetVertex, edge);
			}

			currentVertex = getNextVertex();
			continueCalculating = !_openSet.isEmpty();
		}
		addCalculatedHeuristic();
	}

	/**
	 * Creates a DijkstraDataTable with given input
	 * 
	 * @param graph Graph on which the Dijkstra is executed
	 * @param startVertex Vertex from which the Dijkstra starts searching
	 * @return A dataTable which can be used in the overlying Dijkstra implementation
	 */
	private void createDijkstraDataTable( V startVertex){
		_dataTable = new HashMap<>();

		DijkstraDataTableEntry firstTableEntry = new DijkstraDataTableEntry();
		firstTableEntry.setHeuristic(0);
		_dataTable.put(startVertex, firstTableEntry);
		Set<V> vertexSet = _graph.vertexSet();
		vertexSet.remove(startVertex);
		for (V vertex : vertexSet) {
			_dataTable.put(vertex, new DijkstraDataTableEntry());
		}
	}

	/**
	 * Returns the next Vertex to be used in the DijkstraShortestPath implementation.
	 * 
	 * @return Vertex to be used next
	 */
	private V getNextVertex(){
		// TODO make this safe
		V nextVertex = null;
		double minHeuristic = Double.POSITIVE_INFINITY;
		Iterator<V> openSetIterator = _openSet.iterator();

		while(openSetIterator.hasNext()){
			V currentVertex = openSetIterator.next();
			double currentVertexHeuristic = _dataTable.get(currentVertex).getHeuristic();
			if(currentVertexHeuristic < minHeuristic){
				minHeuristic = currentVertexHeuristic;
				nextVertex = currentVertex;
			}
		}
		return nextVertex;
	}
	
	private void processVertex(V currentVertex, V targetVertex, E edge){

		DijkstraDataTableEntry currentVertexData = _dataTable.get(currentVertex);
		DijkstraDataTableEntry targetVertexData = _dataTable.get(targetVertex);

		if(!_closedSet.contains(targetVertex)){// If shortest path to currentVertex was not found yet
			_openSet.add(targetVertex);
			double heuristicSum = currentVertexData.getHeuristic()+_graph.getEdgeWeight(edge);
			if(heuristicSum < targetVertexData.getHeuristic()){
				targetVertexData.setHeuristic(heuristicSum);
			}
		}
	}

	private void addCalculatedHeuristic(){
		Set<V> vertexSet = _graph.vertexSet();
		for (V vertex : vertexSet) {
			if (_closedSet.contains(vertex)) {
				vertex.setAttribute(_dataTable.get(vertex).getHeuristic());
			} else {
				vertex.setAttribute(Double.POSITIVE_INFINITY);
			}
		}
	}
	
	
	private class DijkstraDataTableEntry{
		private double _heuristic;

		public DijkstraDataTableEntry(){
			_heuristic = Double.POSITIVE_INFINITY;
		}

		/**
		 * @return the _distance
		 */
		public double getHeuristic() {
			return _heuristic;
		}

		/**
		 * @param _distance the _distance to set
		 */
		public void setHeuristic(double _distance) {
			this._heuristic = _distance;
		}
		
		
	}
}
