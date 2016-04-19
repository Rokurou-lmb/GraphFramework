package lennart.magnus.borchert.graphFramework.tools.heuristicGenerator;

import java.util.Set;

import org.jgrapht.Graph;

import lennart.magnus.borchert.graphFramework.materials.Vertex;

public class NeutralHeuristicGenerator<V extends Vertex, E> implements HeuristicGenerator<V, E> {

	@Override
	public void calculateHeuristic(Graph<V, E> graph, V endVertex) {
		Set<V> vertexSet = graph.vertexSet();
		for (V vertex : vertexSet) {
			vertex.setAttribute(0);
		}
	}
}