package lennart.magnus.borchert.GraphFramework.Materials.Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.junit.Before;
import org.junit.Test;

public class FlexibleGraph_T {	// TODO Expand tests

	private GraphParser _parser;
	private String _path;

	private FlexibleGraph<Vertex, Edge> _graph1;
	private FlexibleGraph<Vertex, Edge> _graph2;
	private FlexibleGraph<Vertex, Edge> _graph3;

	private Vertex _v1;
	private Vertex _v2;

	@Before
	public void init() throws IOException, FileFormatException {
		_graph1 = new FlexibleGraph<>(true, true, Edge.class);
		_graph2 = new FlexibleGraph<>(false, false, Edge.class);
		_v1 = new Vertex("1", 0);
		_v2 = new Vertex("2", 0);

		_graph1.addVertex(_v1);
		_graph1.addVertex(_v2);
		Edge edge1 =_graph1.addEdge(_v1, _v2);
		_graph1.setEdgeWeight(edge1, 10);

		_graph2.addVertex(_v1);
		_graph2.addVertex(_v2);
		_graph2.addEdge(_v2, _v1);

		_path = new File(".").getCanonicalPath()+"\\graphs\\junittest2.graph";
		_parser = new GraphParser();
		_graph3 = _parser.parse(_path);
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
		assertEquals(1.0, _graph2.getEdgeWeight(_graph2.getEdge(_v2, _v1)), 0.1);
	}

	@Test
	public void testGetOutgoingEdges(){
		Vertex testVertex = new Vertex("b", 0);
		Set<Edge> outgoingEdgeSet = _graph3.getOutgoingEdges(testVertex);
		assertTrue(outgoingEdgeSet.size() == 2);
		for (Edge edge : outgoingEdgeSet) {
			assertTrue(_graph3.getEdgeSource(edge).equals(testVertex));
		}
	}

	@Test
	public void testGetIncomingEdges(){
		Vertex testVertex = new Vertex("d", 0);
		Set<Edge> incomingEdgeSet = _graph3.getIncomingEdges(testVertex);
		assertTrue(incomingEdgeSet.size() == 2);
		for (Edge edge : incomingEdgeSet) {
			assertTrue(_graph3.getEdgeTarget(edge).equals(testVertex));
		}
	}
}
