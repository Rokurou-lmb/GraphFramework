package lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.Tests;

import static org.junit.Assert.*;

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

import org.jgrapht.GraphPath;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathAlgorithmImplementation_T {

	private GraphParser _parser;
	private String _path;

	private FlexibleGraph<Vertex, Edge> _graph1;

	@Before
	public void init() throws IOException{
		_path = new File(".").getCanonicalPath()+"\\graphs\\";
		_parser = new GraphParser();
	}



	@Test
	public void testAllImplementationsAgainstEachother() throws FileFormatException {
		String file = "bsp1.graph";
		_graph1 = _parser.parse(_path+file);
		Vertex startVertex = new Vertex("a", 1);
		ShortestPathAlgorithm<Vertex, Edge> breadthFirstSearch = new BreadthFirstSearchShortestPath<>();
		ShortestPathAlgorithm<Vertex, Edge> dijkstraShortestPath = new DijkstraShortestPath<>();
		ShortestPathAlgorithm<Vertex, Edge> aStarShortestPath = new AStarShortestPath<>();

		List<Vertex> breadthFirstSearchList;
		List<Vertex> dijkstraShortestPathList;
		List<Vertex> aStarShortestPathList;
		System.out.println("File to be parsed and tested with: "+file);
		Set<Vertex> vertexSet = _graph1.vertexSet();
		for (Vertex endVertex : vertexSet) {
			org.jgrapht.alg.DijkstraShortestPath<Vertex, Edge> dijkstraSafe = new org.jgrapht.alg.DijkstraShortestPath<>(_graph1, startVertex, endVertex);
			breadthFirstSearchList = breadthFirstSearch.findShortestPath(_graph1, startVertex, endVertex);
			dijkstraShortestPathList = dijkstraShortestPath.findShortestPath(_graph1, startVertex, endVertex);
			aStarShortestPathList = aStarShortestPath.findShortestPath(_graph1, startVertex, endVertex);

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

			assertEquals(breadthFirstSearchList.size(),dijkstraSafeList.size());
			assertEquals(dijkstraShortestPathList.size(),dijkstraSafeList.size());
			assertEquals(aStarShortestPathList.size(),dijkstraSafeList.size());
		}
	}

}
