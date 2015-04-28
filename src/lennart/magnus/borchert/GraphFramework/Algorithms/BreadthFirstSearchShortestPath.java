package lennart.magnus.borchert.GraphFramework.Algorithms;

import java.util.List;

import org.jgrapht.Graph;


public class BreadthFirstSearchShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{
	
	private BreadthFirstSearchShortestPath(){
		
	};
	
	public BreadthFirstSearchShortestPath<V, E> getInstance(){
		return (BreadthFirstSearchShortestPath<V, E>)super.getInstance();
	}

	@Override
	public List<V> findShortestPath(Graph<V, E> graph, V startVertex, V endVertex) {
		return super.findShortestPath(graph, startVertex, endVertex);
	}

	@Override
	protected AbstractShortestPathAlgorithm<V, E> InstanceExists() {
		if (super._instance == null) {
			super._instance = new BreadthFirstSearchShortestPath<>();
		}
		return super._instance;
	}

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex,
			V endVertex) {
		// TODO Auto-generated method stub
		return null;
	}
}