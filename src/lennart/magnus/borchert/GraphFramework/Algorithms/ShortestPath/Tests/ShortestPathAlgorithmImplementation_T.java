package lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.AStarShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.BreadthFirstSearchShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.DijkstraShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.ShortestPathAlgorithm;
import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.GraphGenerator;

import org.jgrapht.GraphPath;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathAlgorithmImplementation_T {

	private GraphGenerator _graphGenerator;
	private GraphParser _parser;
	private String _path;
	private ShortestPathAlgorithm<Vertex, Edge> _aStarShortestPath;
	private ShortestPathAlgorithm<Vertex, Edge> _dijkstraShortestPath;
	ShortestPathAlgorithm<Vertex, Edge> _breadthFirstSearch;

	private FlexibleGraph<Vertex, Edge> _graph1;

	@Before
	public void init() throws IOException{
		_graphGenerator = new GraphGenerator();
		_path = new File(".").getCanonicalPath()+"\\graphs\\";
		_parser = new GraphParser();
		_breadthFirstSearch = new BreadthFirstSearchShortestPath<>();
		_dijkstraShortestPath = new DijkstraShortestPath<>();
		_aStarShortestPath = new AStarShortestPath<>();
	}



	@Test
	public void testAllImplementationsAgainstEachother() throws FileFormatException {
		String file = "bsp1.graph";
		_graph1 = _parser.parse(_path+file);
		Vertex startVertex = new Vertex("a", 1);


		List<Vertex> breadthFirstSearchList;
		List<Vertex> dijkstraShortestPathList;
		List<Vertex> aStarShortestPathList;
		System.out.println("File to be parsed and tested with: "+file);
		Set<Vertex> vertexSet = _graph1.vertexSet();
		for (Vertex endVertex : vertexSet) {
			org.jgrapht.alg.DijkstraShortestPath<Vertex, Edge> dijkstraSafe = new org.jgrapht.alg.DijkstraShortestPath<>(_graph1, startVertex, endVertex);
			breadthFirstSearchList = _breadthFirstSearch.findShortestPath(_graph1, startVertex, endVertex);
			dijkstraShortestPathList = _dijkstraShortestPath.findShortestPath(_graph1, startVertex, endVertex);
			aStarShortestPathList = _aStarShortestPath.findShortestPath(_graph1, startVertex, endVertex);

			GraphPath<Vertex, Edge> dijkstraPathSafe = dijkstraSafe.getPath();
			List<Vertex> dijkstraSafeList = new ArrayList<>();
			dijkstraSafeList.add(dijkstraPathSafe.getStartVertex());
			List<Edge> dijkstraSafeEdgeList = dijkstraSafe.getPathEdgeList();
			if(dijkstraSafeEdgeList != null){
				for (Edge edge : dijkstraSafeEdgeList) {
					dijkstraSafeList.add(_graph1.getEdgeTarget(edge));
				}
			}

			System.out.println("Shortest path from "+startVertex.getIdentifier()+" to "+endVertex.getIdentifier()+" with BFS:               "+breadthFirstSearchList.toString());
			System.out.println("Shortest path from "+startVertex.getIdentifier()+" to "+endVertex.getIdentifier()+" with Dijkstra:          "+dijkstraShortestPathList.toString());
			System.out.println("Shortest path from "+startVertex.getIdentifier()+" to "+endVertex.getIdentifier()+" with AStar:             "+aStarShortestPathList.toString());
			System.out.println("Shortest path from "+startVertex.getIdentifier()+" to "+endVertex.getIdentifier()+" with Dijkstra(failsafe):"+dijkstraSafeList.toString());
			System.out.println("");
			assertEquals(breadthFirstSearchList.size(),dijkstraSafeList.size());
			assertEquals(dijkstraShortestPathList.size(),dijkstraSafeList.size());
			assertEquals(aStarShortestPathList.size(),dijkstraSafeList.size());
		}
	}
	
	@Test
	public void testBIG(){
		List<Vertex> breadthFirstSearchList;
		List<Vertex> dijkstraShortestPathList;
		double dijkstraDistance = 0;
		double AStarDistance = 0;
		double dijkstraDistanceSafe = 0;
		List<Vertex> aStarShortestPathList;
		
		FlexibleGraph<Vertex, Edge> graph = _graphGenerator.generateDirectedWeightedGraph(Edge.class, 100, 6000, 100);
		
		Set<Vertex> bigVertexSet = graph.vertexSet();
		List<Vertex> bigVertexList = new ArrayList<>();
		bigVertexList.addAll(bigVertexSet);

		Vertex startVertex = bigVertexList.get(0);
		Vertex endVertex = bigVertexList.get(bigVertexList.size()-1);
		
		org.jgrapht.alg.DijkstraShortestPath<Vertex, Edge> dijkstraSafe = new org.jgrapht.alg.DijkstraShortestPath<>(graph, startVertex, endVertex);
		GraphPath<Vertex, Edge> dijkstraPathSafe = dijkstraSafe.getPath();
		List<Vertex> dijkstraSafeList = new ArrayList<>();
		dijkstraSafeList.add(dijkstraPathSafe.getStartVertex());
		List<Edge> dijkstraSafeEdgeList = dijkstraSafe.getPathEdgeList();
		if(dijkstraSafeEdgeList != null){
			for (Edge edge : dijkstraSafeEdgeList) {
				dijkstraSafeList.add(graph.getEdgeTarget(edge));
			}
		}

		breadthFirstSearchList = _breadthFirstSearch.findShortestPath(graph, startVertex, endVertex);
		dijkstraShortestPathList = _dijkstraShortestPath.findShortestPath(graph, startVertex, endVertex);
		aStarShortestPathList = _aStarShortestPath.findShortestPath(graph, startVertex, endVertex);

		for(int i = 0; i < dijkstraShortestPathList.size()-1; i++){
			dijkstraDistance += graph.getEdgeWeight(graph.getEdge(dijkstraShortestPathList.get(i), dijkstraShortestPathList.get(i+1)));
		}
		
		for(int i = 0; i < aStarShortestPathList.size()-1; i++){
			AStarDistance += graph.getEdgeWeight(graph.getEdge(aStarShortestPathList.get(i), aStarShortestPathList.get(i+1)));
		}

		assertEquals(breadthFirstSearchList.size(),dijkstraSafeList.size());
		assertEquals(dijkstraShortestPathList.size(),dijkstraSafeList.size());
		assertEquals(aStarShortestPathList.size(),dijkstraSafeList.size());
		
		dijkstraDistanceSafe = dijkstraPathSafe.getWeight();
		assertEquals(dijkstraDistance,dijkstraDistanceSafe, 0.1);
		assertEquals(AStarDistance,dijkstraDistanceSafe, 0.1);
	}

}
