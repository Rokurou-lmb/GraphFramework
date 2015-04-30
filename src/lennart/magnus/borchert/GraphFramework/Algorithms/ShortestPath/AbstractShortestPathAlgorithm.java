package lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

public abstract class AbstractShortestPathAlgorithm<V, E> implements ShortestPathAlgorithm<V, E>{

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex){
		List<V> path = new ArrayList<>();
		if(graph.containsVertex(startVertex) && graph.containsVertex(endVertex))
			path = shortestPathHelper(graph, startVertex, endVertex);

		return path;
	}

	protected abstract List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex);
}
