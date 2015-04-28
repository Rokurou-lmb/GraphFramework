package lennart.magnus.borchert.GraphFramework.FileIO.Tests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.junit.Before;
import org.junit.Test;

public class GraphParser_T {

	private String _path;

	private FlexibleGraph<Vertex, Edge> _graph1;
	
	


	@Before
	public void init() {
		try {
			_path = new File(".").getCanonicalPath()+"\\graphs\\junittest.graph";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GraphParser parser = new GraphParser();
		try {
			_graph1 = parser.parse(_path);
		} catch (FileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
