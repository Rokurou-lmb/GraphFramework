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

	private BreadthFirstSearchShortestPath(){}

	public BreadthFirstSearchShortestPath<V, E> getInstance(){
		return (BreadthFirstSearchShortestPath<V, E>)super.getInstance();
	}

	@Override
	protected AbstractShortestPathAlgorithm<V, E> InstanceExists() {
		if (super._instance == null) {
			super._instance = new BreadthFirstSearchShortestPath<>();
		}
		return super._instance;
	}

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		LinkedList<V> path = new LinkedList<V>();

		Queue<V> queue = new LinkedList<>();
		queue.add(startVertex);

		Map<V, Integer> distanceMap = new HashMap<>();
		Integer distance = new Integer(1);
		distanceMap.put(startVertex, distance);

		boolean endVertexFound = false;
		Set<E> currentEdges;
		V sourceVertex;
		V targetVertex = null;

		while(!queue.isEmpty() && !endVertexFound){
			sourceVertex = queue.poll();
			//TODO: find a better way to solve the casting problem
			currentEdges = ((FlexibleGraph<V, E>)graph).getOutgoingEdges(sourceVertex); 
			for (E edge : currentEdges) {
				targetVertex = graph.getEdgeTarget(edge);
				queue.add(targetVertex);
				distanceMap.put(targetVertex, distanceMap.get(sourceVertex)+1);

				endVertexFound = targetVertex.equals(endVertex);
			}
		}
		if(endVertexFound){ //Wenn ein Weg vorhanden ist, verfolge diesen zurück zum Startknoten.
			int remainingPathElements = distanceMap.get(targetVertex);
			while(remainingPathElements > 0){
				Set<E> incomingEdges = ((FlexibleGraph<V, E>)graph).getIncomingEdges(targetVertex);
				Iterator<E> incomingEdgeIterator = incomingEdges.iterator();
				boolean foundSource = false;
				E edge;
				while(incomingEdgeIterator.hasNext() && !foundSource) {
					edge = incomingEdgeIterator.next();
					if(distanceMap.get(edge).equals(new Integer(remainingPathElements-1))){
						foundSource = true;
						path.addFirst(graph.getEdgeSource(edge));
						remainingPathElements--;
					}
				}
			}
		}
		return path;
	}
}