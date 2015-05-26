package lennart.magnus.borchert.GraphFramework.Tools.Tests;

import static org.junit.Assert.*;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.GraphGenerator;

import org.jgrapht.Graph;
import org.junit.Test;

public class GraphGenerator_T {

	private static final int VERTEX_COUNT = 20;
	private static final int EDGE_COUNT = 50;
	private static final int EDGE_WEIGHT_LIMIT = 100;
	
	@Test
	public void test() {
		Graph<Vertex, Edge> graph = new FlexibleGraph<>(false, true, Edge.class);
		GraphGenerator generator = new GraphGenerator();
		generator.generateDirectedWeightedGraph(Edge.class, VERTEX_COUNT, EDGE_COUNT, EDGE_WEIGHT_LIMIT);

		assertEquals(graph.vertexSet().size(),VERTEX_COUNT);
		assertEquals(graph.edgeSet().size(),EDGE_COUNT);
		
	}

}
