package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;

import org.jgrapht.Graph;

public class BreadthFirstSearchShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		LinkedList<V> path = new LinkedList<V>();

		Queue<V> queue = new LinkedList<>();
		queue.add(startVertex);

		Map<V, Integer> distanceMap = new HashMap<>();
		Integer distance = new Integer(0);
		//TODO replace Map with Tree for easier backtracing
		distanceMap.put(startVertex, distance);

		boolean endVertexFound = false;
		Set<E> outgoingEdges;
		V sourceVertex;
		V targetVertex = null;

		while(!queue.isEmpty() && !endVertexFound){
			sourceVertex = queue.poll();
			//TODO: find a better way to solve the casting problem
			outgoingEdges = ((FlexibleGraph<V, E>)graph).getOutgoingEdges(sourceVertex); 
			Iterator<E> ougoingEdgesIterator = outgoingEdges.iterator();
			while(ougoingEdgesIterator.hasNext() && !endVertexFound) {
				E edge = ougoingEdgesIterator.next();
				targetVertex = graph.getEdgeTarget(edge);
				if(!distanceMap.containsKey(targetVertex)){// Add newly found vertex to queue and distanceMap
					queue.add(targetVertex);
					distanceMap.put(targetVertex, distanceMap.get(sourceVertex)+1);
				}
				endVertexFound = targetVertex.equals(endVertex);
			}
		}
		if(endVertexFound){ //Wenn ein Weg vorhanden ist, verfolge diesen zurück zum Startknoten.
			path = traceTakenPath(graph, distanceMap, startVertex, endVertex);
		}
		return path;
	}


	/**
	 * Traces the shortest path back from the endVertex to the startVertex, using the filled distanceMap, 
	 * created by the BreadthFirstSearchShortestPath implementation.
	 * 
	 * @param graph 
	 * @param distanceMap 
	 * @param startVertex 
	 * @param endVertex 
	 * @return 
	 */
	private LinkedList<V> traceTakenPath(Graph<V, E> graph, Map<V, Integer> distanceMap, V startVertex, V endVertex){
		LinkedList<V> path = new LinkedList<>();
		V targetVertex = endVertex;
		V sourceVertex;
		int remainingPathElements = distanceMap.get(targetVertex);
		boolean foundSource;

		path.addFirst(targetVertex);
		while(remainingPathElements > 0){
			Set<E> incomingEdges = ((FlexibleGraph<V, E>)graph).getIncomingEdges(targetVertex);
			Iterator<E> incomingEdgeIterator = incomingEdges.iterator();
			foundSource = false;
			E edge;
			while(incomingEdgeIterator.hasNext() && !foundSource) { //iterate over all incoming edges
				edge = incomingEdgeIterator.next();
				sourceVertex = graph.getEdgeSource(edge);
				if(distanceMap.containsKey(sourceVertex)){
					if(distanceMap.get(sourceVertex).equals(new Integer(remainingPathElements-1))){
						foundSource = true;
						targetVertex = graph.getEdgeSource(edge);
						path.addFirst(targetVertex);
						remainingPathElements--;
					}
				}
			}
		}
		return path;
	}
}