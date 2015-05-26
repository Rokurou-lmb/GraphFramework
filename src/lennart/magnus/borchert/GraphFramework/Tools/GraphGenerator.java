package lennart.magnus.borchert.GraphFramework.Tools;


import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

public class GraphGenerator {

	/**
	 * Fills the given Graph 
	 * 
	 * @param graph
	 * @param vertexCount
	 * @param edgeCount
	 * @return
	 */
	private void fillGraphRandomly(FlexibleGraph<Vertex, Edge> graph, int vertexCount, int edgeCount, int edgeWeightUpperLimit){

		List<Vertex> vertexList= new ArrayList<>();
		Random randomVertexNumberGenerator = new Random();
		IntStream vertexNumberStream = randomVertexNumberGenerator.ints(edgeCount*2, 0, vertexCount);
		PrimitiveIterator.OfInt vertexNumberIterator = vertexNumberStream.iterator();
		
		Random randomWeightGenerator = new Random();
		IntStream weightStream = randomWeightGenerator.ints(edgeCount, 0, edgeWeightUpperLimit);
		PrimitiveIterator.OfInt weightIterator = weightStream.iterator();

		for(int i = 0; i < vertexCount; i++){
			Vertex vertex = new Vertex(Integer.toString(i));
			graph.addVertex(vertex);
			vertexList.add(vertex);
		}
		Edge edge;
		for(int j = 0; j < edgeCount; j++){
			edge = new Edge();
			graph.addEdge(vertexList.get(vertexNumberIterator.nextInt()), vertexList.get(vertexNumberIterator.nextInt()), edge);
			graph.setEdgeWeight(edge, weightIterator.nextInt());
		}
	}

	private FlexibleGraph<Vertex, Edge> generateGraph(boolean directed, boolean weighted, Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		FlexibleGraph<Vertex, Edge> graph = new FlexibleGraph<>(directed, weighted, edgeClass);
		fillGraphRandomly(graph, vertexCount, edgeCount, edgeWeightUpperLimit);
		return graph;
	}

	public FlexibleGraph<Vertex, Edge> generateUndirectedUnweightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateGraph(false, false, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}

	public FlexibleGraph<Vertex, Edge> generateDirectedUnweightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateGraph(true, false, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}

	public FlexibleGraph<Vertex, Edge> generateUndirectedWeightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateGraph(false, true, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}

	public FlexibleGraph<Vertex, Edge> generateDirectedWeightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateGraph(true, true, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}
}
