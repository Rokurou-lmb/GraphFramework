package lennart.magnus.borchert.GraphFramework.Tools;


import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.IntStream;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
//TODO add eulergraph generation
public class GraphGenerator {

	private FlexibleGraph<Vertex, Edge> generateRandomGraph(boolean directed, boolean weighted, Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		FlexibleGraph<Vertex, Edge> graph = new FlexibleGraph<>(directed, weighted, edgeClass);
		fillGraphRandomly(graph, vertexCount, edgeCount, edgeWeightUpperLimit);
		return graph;
	}

	public FlexibleGraph<Vertex, Edge> generateUndirectedUnweightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateRandomGraph(false, false, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}

	public FlexibleGraph<Vertex, Edge> generateDirectedUnweightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateRandomGraph(true, false, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}

	public FlexibleGraph<Vertex, Edge> generateUndirectedWeightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateRandomGraph(false, true, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}

	public FlexibleGraph<Vertex, Edge> generateDirectedWeightedGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount, int edgeWeightUpperLimit){
		return generateRandomGraph(true, true, edgeClass, vertexCount, edgeCount, edgeWeightUpperLimit);
	}
	
	/**
	 * Creates an unweighted, undirected EulerGraph.
	 * 
	 * @param edgeClass
	 * @param vertexCount
	 * @param edgeCount
	 * @param edgeWeightUpperLimit
	 * @return
	 */
	public FlexibleGraph<Vertex, Edge> generateEulerGraph(Class<? extends Edge> edgeClass, int vertexCount, int edgeCount){
		FlexibleGraph<Vertex, Edge> graph = new FlexibleGraph<>(false, false, edgeClass);
		fillEulerGraph(graph, vertexCount, edgeCount);
		return graph;
	}

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
		IntStream weightStream = randomWeightGenerator.ints(edgeCount, 1, edgeWeightUpperLimit);
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

	/**
	 * 
	 * @param graph The graph that is going to be filled
	 * @param vertexCount number of wanted vertices in the graph.
	 * @param edgeCount number of wanted vertices in the graph.
	 */
	private void fillEulerGraph(FlexibleGraph<Vertex, Edge> graph, int vertexCount, int edgeCount){

		Random rNG = new Random();
		IntStream vertexNumberStream = rNG.ints(edgeCount*2, 0, vertexCount);
		PrimitiveIterator.OfInt vertexNumberIterator = vertexNumberStream.iterator();

		List<Vertex> unevenDegreeList = new ArrayList<>();
		List<Vertex> joinedVertexList = new ArrayList<>();
		List<Vertex> vertexList = new ArrayList<>();

		for (int i = 0; i < vertexCount; i++) {
			Vertex vertex = new Vertex(Integer.toString(i));
			graph.addVertex(vertex);
			vertexList.add(vertex);
		}
		joinedVertexList.add(new Vertex("0")); //This isn't completley unprejudiced

		Vertex sourceVertex;
		Vertex targetVertex;

		//while not all vertices are connected we have to make sure that all edges stay connected with eachother.
		//edgeCount-2 so we can make sure, that the structure is correct
		int addedEdgeCount = 0;
		for(; joinedVertexList.size() < vertexList.size() && addedEdgeCount < edgeCount - 2; addedEdgeCount++){

			sourceVertex = joinedVertexList.get(rNG.nextInt(joinedVertexList.size()));
			if(unevenDegreeList.size() > 2){
				targetVertex = unevenDegreeList.get(rNG.nextInt(unevenDegreeList.size()));
			} else {
				targetVertex = vertexList.get(vertexNumberIterator.nextInt());
			}
			graph.addEdge(sourceVertex, targetVertex);
			updateDegreeList(unevenDegreeList, sourceVertex, targetVertex);
		}

		//When all vertices are connected we can choose freely
		for (; addedEdgeCount < edgeCount - 2; addedEdgeCount++) {
			sourceVertex = vertexList.get(vertexNumberIterator.nextInt());
			if(unevenDegreeList.size() > 2){
				targetVertex = unevenDegreeList.get(rNG.nextInt(unevenDegreeList.size()));
			} else {
				targetVertex = vertexList.get(vertexNumberIterator.nextInt());
			}
			graph.addEdge(sourceVertex, targetVertex);
			updateDegreeList(unevenDegreeList, sourceVertex, targetVertex);
		}
		
		switch(unevenDegreeList.size()){
			case(0):
				break;
			case(2):
				break;
			case(4):
				break;
			default: //Debug usage only, should be properly tested separately
				throw new IllegalStateException("The generated graph didn't conform to its rules, the generation Implementation is faulty.");
		}
		
	}

	/**
	 * This method is to be called after adding a new Edge to a EulerGraph 
	 * and will update the Entries related to both the source and target vertices.
	 * 
	 * @param unevenDegreeList maintained list of all vertices with an uneven degree
	 * @param sourceVertex of the newly added Edge
	 * @param targetVertex of the newly added Edge
	 */
	private void updateDegreeList(List<Vertex> unevenDegreeList, Vertex sourceVertex, Vertex targetVertex){
		//TODO: the used collection is highly ineffective, maybe replace it or generate a set backed by the original list.
		if (unevenDegreeList.contains(sourceVertex)) {
			unevenDegreeList.remove(sourceVertex);
		}else{
			unevenDegreeList.add(sourceVertex);
		}
		if (unevenDegreeList.contains(targetVertex)) {
			unevenDegreeList.remove(targetVertex);
		}else{
			unevenDegreeList.add(targetVertex);
		}
	}
}