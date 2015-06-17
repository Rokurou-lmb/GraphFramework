package lennart.magnus.borchert.GraphFramework.Algorithms.EulerTour.Tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lennart.magnus.borchert.GraphFramework.Algorithms.EulerTour.EulerTourAlgorithm;
import lennart.magnus.borchert.GraphFramework.Algorithms.EulerTour.FleuryEulerTour;
import lennart.magnus.borchert.GraphFramework.Algorithms.EulerTour.HierholzerEulerTour;
import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.GraphGenerator;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.junit.Before;
import org.junit.Test;

public class EulerTourAlgorithmTest {

	private String									_path;
	private GraphParser								_parser;
	private GraphGenerator							_generator;
	private List<EulerTourAlgorithm<Vertex, Edge>>	_algorithmList;
	private EulerTourAlgorithm<Vertex, Edge>		_fleuryEulerCircle;
	private EulerTourAlgorithm<Vertex, Edge>		_hierholzerEulerCircle;
	private final static String						_file1	= "eulertest1.graph";
	private final static String						_file2	= "eulertest2.graph";
	private final static String						_file3	= "eulertest3.graph";
	private Graph<Vertex, Edge>						_eulerGraph1;
	private Graph<Vertex, Edge>						_eulerGraph2;
	private Graph<Vertex, Edge>						_nonEulerGraph1;

	@Before
	public void setUp() {
		initializeGraphs();
		_generator = new GraphGenerator();
		_algorithmList = new ArrayList<>();
		_fleuryEulerCircle = new FleuryEulerTour<>();
		_hierholzerEulerCircle = new HierholzerEulerTour<>();

		_algorithmList.add(_fleuryEulerCircle);
		_algorithmList.add(_hierholzerEulerCircle);
	}

	private void initializeGraphs() {
		_parser = new GraphParser();
		try {
			_path = new File(".").getCanonicalPath() + "\\graphs\\junitTestGraphs\\eulerTourTestGraphs\\";
		} catch (IOException e) {
		}
		try {
			_eulerGraph1 = _parser.parse(_path + _file1);
			_eulerGraph2 = _parser.parse(_path + _file2);
			_nonEulerGraph1 = _parser.parse(_path + _file3);
		} catch (FileFormatException e) {
		}
	}

	@Test
	public void eulerTourHasBeenFound() {
		for (EulerTourAlgorithm<Vertex, Edge> eulerCircleAlgorithm : _algorithmList) {
			assert (eulerCircleAlgorithm.findEulerCircle(_eulerGraph1).getEdgeList().size() > 0);
			assert (eulerCircleAlgorithm.findEulerCircle(_eulerGraph2).getEdgeList().size() > 0);
			assert (eulerCircleAlgorithm.findEulerCircle(_nonEulerGraph1).getEdgeList().size() == 0);
		}
	}

	@Test
	public void eulerTourIsComplete() {
		for (EulerTourAlgorithm<Vertex, Edge> eulerCircleAlgorithm : _algorithmList) {
			assert (eulerCircleAlgorithm.findEulerCircle(_eulerGraph1).getEdgeList().containsAll(_eulerGraph1.edgeSet()));
			assert (eulerCircleAlgorithm.findEulerCircle(_eulerGraph2).getEdgeList().containsAll(_eulerGraph2.edgeSet()));
		}
	}

	@Test
	public void testGeneratedEulerTourGraphs() {
		for (EulerTourAlgorithm<Vertex, Edge> eulerCircleAlgorithm : _algorithmList) {
			for (int i = 1; i < 20; i++) {
				Graph<Vertex, Edge> generatedEulerGraph = _generator.generateEulerGraph(Edge.class, i, i+2);
				GraphPath<Vertex, Edge> graphPath = eulerCircleAlgorithm.findEulerCircle(generatedEulerGraph);
				assert(graphPath.getEdgeList().containsAll(generatedEulerGraph.edgeSet()));
				Vertex startVertex = graphPath.getStartVertex();
				Vertex currentVertex = startVertex;
				for(Edge edge : graphPath.getEdgeList()){
					assert(generatedEulerGraph.getEdgeTarget(edge).equals(currentVertex) || generatedEulerGraph.getEdgeSource(edge).equals(currentVertex));
					currentVertex = getOtherVertexTouchedByEdge(generatedEulerGraph, edge, currentVertex);
				}
				assert(currentVertex.equals(startVertex));
			}
		}
	}
	
	private Vertex getOtherVertexTouchedByEdge(Graph<Vertex, Edge> graph, Edge edge, Vertex vertex){
		return (graph.getEdgeTarget(edge).equals(vertex)) ? graph.getEdgeSource(edge) : graph.getEdgeTarget(edge);
	}
//	 @Test
//	 public void FleuryEulerCircleTest() {
//	 		fail("Not yet implemented");
//	 }
//
//	 @Test
//	 public void HierholzerEulerCircleTest() {
//		 fail("Not yet implemented");
//	 }
}