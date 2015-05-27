package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder;

import org.jgrapht.Graph;

public class AbstractVertexFinder<V, E> implements VertexFinder<V, E>{
	protected Graph<V, E>_graph;
	

	public AbstractVertexFinder(Graph<V, E> graph){
		_graph = graph;
	}

	@Override
	public V getVertex() {
		return null;
	}
}
