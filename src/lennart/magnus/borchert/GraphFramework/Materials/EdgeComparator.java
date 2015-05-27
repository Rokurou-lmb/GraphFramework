package lennart.magnus.borchert.GraphFramework.Materials;

import java.util.Comparator;

import org.jgrapht.Graph;

public class EdgeComparator<V, E> implements Comparator<E>{
	
	private Graph<V, E> _graph;

	public EdgeComparator(Graph<V, E> graph) {
		_graph = graph;
	}

	@Override
	public int compare(E o1, E o2) {
		return Double.compare(_graph.getEdgeWeight(o1), _graph.getEdgeWeight(o2));
	}
}
