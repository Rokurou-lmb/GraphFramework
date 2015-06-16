package lennart.magnus.borchert.GraphFramework.Tools.Tests;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.AStarShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.DijkstraShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.ShortestPathAlgorithm;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.GraphGenerator;
import lennart.magnus.borchert.GraphFramework.Tools.WeightedGraphTools;
import lennart.magnus.borchert.GraphFramework.Tools.HeuristicGenerator.DijkstraPerfectHeuristicGenerator;

import org.junit.Before;
import org.junit.Test;

public class GraphGenerator_T {

	private static final int VERTEX_COUNT = 5;
	private static final int EDGE_COUNT = 15;
	private static final int EDGE_WEIGHT_LIMIT = 10;
	private FlexibleGraph<Vertex, Edge> _graph;
	private FlexibleGraph<Vertex, Edge> _eulerGraph;
	private ShortestPathAlgorithm<Vertex, Edge> _aStarShortestPath;
	private ShortestPathAlgorithm<Vertex, Edge> _dijkstraShortestPath;


	@Before
	public void setUp(){
		GraphGenerator generator = new GraphGenerator();
		_graph = generator.generateDirectedWeightedGraph(Edge.class, VERTEX_COUNT, EDGE_COUNT, EDGE_WEIGHT_LIMIT);
		_eulerGraph = generator.generateEulerGraph(Edge.class, VERTEX_COUNT, EDGE_COUNT);
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
		WeightedGraphTools<Vertex, Edge> graphTool = new WeightedGraphTools<>();
		DijkstraPerfectHeuristicGenerator<Vertex, Edge> heuristicGenerator = new DijkstraPerfectHeuristicGenerator<>();
		Vertex endVertex = new Vertex("1");

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

			assert(dijkstraDistance == vertex.getAttribute() || Double.isInfinite(vertex.getAttribute()));

			for(int i = 0; i < aStarShortestPathList.size()-1; i++){
				AStarDistance += _graph.getEdgeWeight(graphTool.findLightestEdge(_graph, aStarShortestPathList.get(i), aStarShortestPathList.get(i+1)));
			}

			assert(AStarDistance == vertex.getAttribute() || Double.isInfinite(vertex.getAttribute()));
		}
	}
	
	@Test
	public void EulerGraphGeneratorTest(){
		assert(_eulerGraph != null);
		assert(_eulerGraph.vertexSet().size() == VERTEX_COUNT);
		assert(_eulerGraph.edgeSet().size() == EDGE_COUNT);
		Set<Vertex> vertexSet = _eulerGraph.vertexSet();
		for (Vertex vertex : vertexSet) {
			assert((_eulerGraph.edgesOf(vertex).size() % 2) == 0);
		}

		
	}
}
