package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder;

import java.util.Set;

import org.jgrapht.Graph;

public class RandomVertexFinder<V, E> extends AbstractVertexFinder<V, E> {
	
	public RandomVertexFinder(Graph<V, E> graph) {
		super(graph);
	}


	@Override
	public V getVertex() {
		Set<V> vertexSet = _graph.vertexSet();
		@SuppressWarnings("unchecked")
		V[] vertexArray = (V[])vertexSet.toArray();
		int lowerBound = 0;
		int upperBound = vertexSet.size();
		return vertexArray[(int)(Math.random() * (upperBound - lowerBound) + lowerBound)];
	}

}
