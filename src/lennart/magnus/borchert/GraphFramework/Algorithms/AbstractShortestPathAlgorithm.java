package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;

public abstract class AbstractShortestPathAlgorithm<V, E> implements ShortestPathAlgorithm<V, E>{
	
	protected AbstractShortestPathAlgorithm<V, E> _instance;

	@Override
	public AbstractShortestPathAlgorithm<V, E> getInstance(){
		return InstanceExists();
	}

	protected abstract AbstractShortestPathAlgorithm<V, E> InstanceExists();


	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex){
		List<V> path = new ArrayList<>();
		if(graph.containsVertex(startVertex) && graph.containsVertex(endVertex))
			path = shortestPathHelper(graph, startVertex, endVertex);

		return path;
	}

	protected abstract List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex);
}
