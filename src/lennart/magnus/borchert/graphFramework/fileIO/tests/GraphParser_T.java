package lennart.magnus.borchert.graphFramework.fileIO.tests;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import lennart.magnus.borchert.graphFramework.fileIO.FileFormatException;
import lennart.magnus.borchert.graphFramework.fileIO.GraphParser;
import lennart.magnus.borchert.graphFramework.materials.Edge;
import lennart.magnus.borchert.graphFramework.materials.FlexibleGraph;
import lennart.magnus.borchert.graphFramework.materials.Vertex;

public class GraphParser_T {

	private String _path;

	private FlexibleGraph<Vertex, Edge> _graph1;


	@Before
	public void init() {
		try {
			_path = new File(".").getCanonicalPath()+"\\graphs\\junitTestGraphs\\junittest.graph";
		} catch (IOException e) {
		}
		GraphParser parser = new GraphParser();
		try {
			_graph1 = parser.parse(_path);
		} catch (FileFormatException e) {
		}
	}
	
	@Test
	public void testOptionsLine() {
		assertTrue(_graph1.isDirected() && _graph1.isWeighted());
	}
	
	@Test
	public void testGraphDefinition() {
		Vertex v1 = new Vertex("a", 0);
		Vertex v2 = new Vertex("b", 0);
		Vertex v3 = new Vertex("c", 0);
		Set<Vertex> vSet = new HashSet<>();
		vSet.add(v1);
		vSet.add(v2);
		vSet.add(v3);
		Set<Vertex> graphVertexSet = _graph1.vertexSet();
		
		assertTrue(graphVertexSet.containsAll(vSet));
		assertTrue(_graph1.containsEdge(v1, v2));
		assertTrue(_graph1.containsEdge(v2, v3));
		assertTrue(_graph1.containsEdge(v3, v1));
		
	}

}
