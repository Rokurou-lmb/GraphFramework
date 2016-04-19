package lennart.magnus.borchert.graphFramework.tools.heuristicGenerator;

import java.util.Set;

import org.jgrapht.Graph;

import lennart.magnus.borchert.graphFramework.materials.Vertex;

public class DijkstraPerfectHeuristicGenerator<V extends Vertex, E> implements HeuristicGenerator<V, E>{

	public void calculateHeuristic(Graph<V, E> graph, V endVertex) { 
		Set<V> vertexSet = graph.vertexSet();
		for (V vertex : vertexSet) {
			org.jgrapht.alg.DijkstraShortestPath<V, E> dijkstraShortestPath = new org.jgrapht.alg.DijkstraShortestPath<>(graph, vertex, endVertex);
			double pathLength = dijkstraShortestPath.getPathLength();
			vertex.setAttribute(pathLength);
		}
	}
}