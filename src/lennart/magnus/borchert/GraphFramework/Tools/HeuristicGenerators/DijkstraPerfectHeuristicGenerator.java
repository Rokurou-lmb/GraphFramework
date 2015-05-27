package lennart.magnus.borchert.GraphFramework.Tools.HeuristicGenerators;

import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.jgrapht.Graph;

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