package lennart.magnus.borchert.GraphFramework.Tools;


import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.jgrapht.Graph;

public class UndirectedGraphGenerator {
	
	/**
	 * Fills a given empty Graph with the given amount of vertices, which form a circle.
	 * 
	 * @param graph to be filled
	 * @param vertexCount number of vertices to insert into graph
	 * @return true if the graph was filled, false if not.
	 */
	public boolean fillGraphWithCircle(Graph<Vertex, Edge> graph, int vertexCount){
		boolean filledGraph = graph.vertexSet().isEmpty() && graph.edgeSet().isEmpty();
		List<Vertex> vertexList= new ArrayList<>();
		if(filledGraph){
			for(int i = 0; i < vertexCount; i++){
				Vertex vertex = new Vertex(Integer.toString(i));
				graph.addVertex(vertex);
				vertexList.add(vertex);
			}
			for(int j = 0; j < vertexCount; j++){
				graph.addEdge(vertexList.get(j), vertexList.get(j+1));
			}
			graph.addEdge(vertexList.get(vertexList.size()-1), vertexList.get(0));
		}
		return filledGraph;
	}
	
	public boolean fillGraphWithWheel(Graph<Vertex, Edge> graph, int vertexCount){
		boolean filledGraph = graph.vertexSet().isEmpty() && graph.edgeSet().isEmpty();
		List<Vertex> vertexList= new ArrayList<>();
		if(filledGraph){
			Vertex centerVertex = new Vertex(Integer.toString(0));
			for(int i = 1; i < vertexCount; i++){
				Vertex vertex = new Vertex(Integer.toString(i));
				graph.addVertex(vertex);
				graph.addEdge(vertex, centerVertex);
				vertexList.add(vertex);
			}
			for(int j = 1; j < vertexCount; j++){
				graph.addEdge(vertexList.get(j), vertexList.get(j+1));
			}
			graph.addEdge(vertexList.get(vertexList.size()-1), vertexList.get(0));
		}
		return filledGraph;
	}
	
	public boolean fillGraphRandomly(Graph<Vertex, Edge> graph, int vertexCount, int edgeCount){
		boolean filledGraph = graph.vertexSet().isEmpty() && graph.edgeSet().isEmpty();
		List<Vertex> vertexList= new ArrayList<>();
		Random randomNumberGenerator = new Random();
		IntStream intStream = randomNumberGenerator.ints(edgeCount, 0, vertexCount+1);
		PrimitiveIterator.OfInt intStreamIterator = intStream.iterator();

		if(filledGraph){
			for(int i = 0; i < vertexCount; i++){
				Vertex vertex = new Vertex(Integer.toString(i));
				graph.addVertex(vertex);
				vertexList.add(vertex);
			}
			for(int j = 0; j < edgeCount; j++){
				graph.addEdge(vertexList.get(intStreamIterator.nextInt()), vertexList.get(intStreamIterator.nextInt()));
			}
			graph.addEdge(vertexList.get(vertexList.size()-1), vertexList.get(0));
		}
		return filledGraph;
	}
}
