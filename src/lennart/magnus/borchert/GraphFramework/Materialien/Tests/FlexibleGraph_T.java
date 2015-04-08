package lennart.magnus.borchert.GraphFramework.Materialien.Tests;

import static org.junit.Assert.*;

import lennart.magnus.borchert.GraphFramework.Materialien.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materialien.Vertex;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.Before;
import org.junit.Test;

public class FlexibleGraph_T {

	private FlexibleGraph<Vertex, DefaultEdge> _graph1;
	private FlexibleGraph<Vertex, DefaultEdge> _graph2;
	private Vertex _v1;
	private Vertex _v2;

	@Before
	public void init() {
		_graph1 = new FlexibleGraph<>(true, true, DefaultWeightedEdge.class);
		_graph2 = new FlexibleGraph<>(false, false, DefaultWeightedEdge.class);
		_v1 = new Vertex("1", 0);
		_v2 = new Vertex("2", 0);

		_graph1.addVertex(_v1);
		_graph1.addVertex(_v2);
		DefaultWeightedEdge edge1 = (DefaultWeightedEdge)_graph1.addEdge(_v1, _v2);
		_graph1.setEdgeWeight(edge1, 10);

		_graph2.addVertex(_v1);
		_graph2.addVertex(_v2);
		DefaultWeightedEdge edge2 = (DefaultWeightedEdge)_graph2.addEdge(_v1, _v2);
		_graph2.setEdgeWeight(edge2, 10);
	}

	@Test
	public void testContainsEdge() {
		assertTrue(_graph1.containsEdge(_v1, _v2));
		assertFalse(_graph1.containsEdge(_v2, _v1));
		
		assertTrue(_graph2.containsEdge(_v1, _v2));
		assertTrue(_graph2.containsEdge(_v2, _v1));
	}

	@Test
	public void testGetEdgeWeight() {
		assertEquals(10, _graph1.getEdgeWeight(_graph1.getEdge(_v1, _v2)),0.1);
		assertNotEquals(10, _graph2.getEdgeWeight(_graph2.getEdge(_v1, _v2)), 0.1);
	}
}
