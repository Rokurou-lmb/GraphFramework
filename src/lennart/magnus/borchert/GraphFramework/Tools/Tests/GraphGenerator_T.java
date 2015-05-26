package lennart.magnus.borchert.GraphFramework.Tools.Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.AStarShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.DijkstraShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.ShortestPathAlgorithm;
import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.DijkstraPerfectHeuristicGenerator;
import lennart.magnus.borchert.GraphFramework.Tools.GraphGenerator;
import lennart.magnus.borchert.GraphFramework.Tools.GraphTools;

import org.junit.Before;
import org.junit.Test;

public class GraphGenerator_T {

	private static final int VERTEX_COUNT = 5;
	private static final int EDGE_COUNT = 10;
	private static final int EDGE_WEIGHT_LIMIT = 10;
	private FlexibleGraph<Vertex, Edge> _graph;
	private ShortestPathAlgorithm<Vertex, Edge> _aStarShortestPath;
	private ShortestPathAlgorithm<Vertex, Edge> _dijkstraShortestPath;
	
	
	@Before
	public void setUp(){
		GraphGenerator generator = new GraphGenerator();
		_graph = generator.generateDirectedWeightedGraph(Edge.class, VERTEX_COUNT, EDGE_COUNT, EDGE_WEIGHT_LIMIT);
		_dijkstraShortestPath = new DijkstraShortestPath<>();
		_aStarShortestPath = new AStarShortestPath<>();
	}
	
	@Test
	public void GraphGeneratorTest() {
		assertEquals(_graph.vertexSet().size(),VERTEX_COUNT);
		assertEquals(_graph.edgeSet().size(),EDGE_COUNT);
	}
	
	@Test
	public void DijkstraPerfectHeuristicGeneratorTest(){
		GraphTools<Vertex, Edge> graphTool = new GraphTools<>();
		DijkstraPerfectHeuristicGenerator<Vertex, Edge> heuristicGenerator = new DijkstraPerfectHeuristicGenerator<>();
		Vertex endVertex = new Vertex("1");


//		try {
//			GraphParser parser = new GraphParser();
//			String path = "";
//			path = new File(".").getCanonicalPath()+"\\graphs\\bsp4.graph";
//			try {
//				_graph = parser.parse(path);
//			} catch (FileFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Vertex endVertex = new Vertex("a");
		
		Set<Vertex> vertexSet = _graph.vertexSet();
		heuristicGenerator.calculateHeuristic(_graph, endVertex);
		for (Vertex vertex : vertexSet) {
			List<Vertex> dijkstraShortestPathList = _dijkstraShortestPath.findShortestPath(_graph, endVertex, vertex);
			List<Vertex> aStarShortestPathList = _aStarShortestPath.findShortestPath(_graph, endVertex, vertex);
			double dijkstraDistance = 0;
			double AStarDistance = 0;

			for(int i = 0; i < dijkstraShortestPathList.size()-1; i++){
				Edge lightestEdge = graphTool.findLightestEdge(_graph, dijkstraShortestPathList.get(i), dijkstraShortestPathList.get(i+1));
				dijkstraDistance += _graph.getEdgeWeight(lightestEdge);
			}
			
			assertEquals(dijkstraDistance, vertex.getAttribute(), 0.1);

			for(int i = 0; i < aStarShortestPathList.size()-1; i++){
				AStarDistance += _graph.getEdgeWeight(graphTool.findLightestEdge(_graph, aStarShortestPathList.get(i), aStarShortestPathList.get(i+1)));
			}

			assertEquals(AStarDistance, vertex.getAttribute(), 0.1);
		}
	}
}
