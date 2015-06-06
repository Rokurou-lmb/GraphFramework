package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.Tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.MinimalSpanningTreeAlgorithm;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.Prim;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder.FibonacciHeapEdgeFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder.PriorityQueueEdgeFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder.RandomVertexFinder;
import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.WeightedGraphTools;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

public class Prim_T {
	
	private String _path;
	private GraphParser _parser;
	private FlexibleGraph<Vertex, Edge>_graph;
	private MinimalSpanningTreeAlgorithm<Vertex, Edge> _primAlgorithm;
	private MinimalSpanningTreeAlgorithm<Vertex, Edge> _primAlgorithmWithFibHeap;
	private Graph<Vertex, Edge> _spanningTree;
	private Graph<Vertex, Edge> _spanningTreeWithFibHeap;
	

	@Before
	public void setUp(){
		_parser = new GraphParser();
		try {
			_path = new File(".").getCanonicalPath()+"\\graphs\\junitTestGraphs\\junittest3.graph";
			try {
				_graph = _parser.parse(_path);
			} catch (FileFormatException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		_primAlgorithm = new Prim<>(new PriorityQueueEdgeFinder<>(_graph), new RandomVertexFinder<>(_graph));
		_primAlgorithmWithFibHeap = new Prim<>(new FibonacciHeapEdgeFinder<>(_graph), new RandomVertexFinder<>(_graph));
		_spanningTree = _primAlgorithm.createMinimalSpanningTree(_graph, Edge.class);
		_spanningTreeWithFibHeap = _primAlgorithmWithFibHeap.createMinimalSpanningTree(_graph, Edge.class);
	}

	@Test
	public void testGraphWasReadSuccessfully(){
		assert(_graph.vertexSet().size() == 4);
		assert(_graph.edgeSet().size() == 9);
	}

	@Test
	public void testSpanningTreeContainsAllVertices(){
		assert(_spanningTree.vertexSet().containsAll(_graph.vertexSet()));
	}

	@Test
	public void testSpanningTreeIsMinimal(){
		WeightedGraphTools<Vertex, Edge> graphTools = new WeightedGraphTools<>();
		assertEquals(6.0, graphTools.getEdgeWeightSum(_spanningTree), 0.1);
	}

	@Test
	public void testSpanningTreeContainsAllVerticesWithFibHeap(){
		assert(_spanningTreeWithFibHeap.vertexSet().containsAll(_graph.vertexSet()));
	}

	@Test
	public void testSpanningTreeIsMinimalWithFibHeap(){
		WeightedGraphTools<Vertex, Edge> graphTools = new WeightedGraphTools<>();
		assertEquals(6.0, graphTools.getEdgeWeightSum(_spanningTreeWithFibHeap), 0.1);
	}
	
	@Test
	public void testImplementationsAgainstEachother(){
		assert(_spanningTree.equals(_spanningTreeWithFibHeap));
	}
}
