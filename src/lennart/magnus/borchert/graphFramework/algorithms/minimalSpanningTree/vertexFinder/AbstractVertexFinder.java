package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.vertexFinder;

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
