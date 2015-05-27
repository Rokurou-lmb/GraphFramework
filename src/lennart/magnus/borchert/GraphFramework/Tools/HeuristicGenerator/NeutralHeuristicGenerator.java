package lennart.magnus.borchert.GraphFramework.Tools.HeuristicGenerator;

import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.jgrapht.Graph;

public class NeutralHeuristicGenerator<V extends Vertex, E> implements HeuristicGenerator<V, E> {

	@Override
	public void calculateHeuristic(Graph<V, E> graph, V endVertex) {
		Set<V> vertexSet = graph.vertexSet();
		for (V vertex : vertexSet) {
			vertex.setAttribute(0);
		}
	}
}