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
		for(int i = 0; i < vertexCount; i++){
			Vertex vertex = new Vertex(Integer.toString(i));
			graph.addVertex(vertex);
			vertexList.add(vertex);
		}

		if(edgeCount > 0) {
			Random randomVertexNumberGenerator = new Random();
			IntStream vertexNumberStream = randomVertexNumberGenerator.ints(edgeCount*2, 0, vertexCount);
			PrimitiveIterator.OfInt vertexNumberIterator = vertexNumberStream.iterator();
	
			Random randomWeightGenerator = new Random();
			IntStream weightStream = randomWeightGenerator.ints(edgeCount, 1, edgeWeightUpperLimit);
			PrimitiveIterator.OfInt weightIterator = weightStream.iterator();
	
			Edge edge;
			for(int j = 0; j < edgeCount; j++){
				edge = new Edge();
				graph.addEdge(vertexList.get(vertexNumberIterator.nextInt()), vertexList.get(vertexNumberIterator.nextInt()), edge);
				graph.setEdgeWeight(edge, weightIterator.nextInt());
			}
		}
	}

	/**
	 * 
	 * @param graph The graph that is going to be filled
	 * @param vertexCount number of wanted vertices in the graph.
	 * @param edgeCount number of wanted vertices in the graph.
	 */
	private void fillEulerGraph(FlexibleGraph<Vertex, Edge> graph, int vertexCount, int edgeCount){

		List<Vertex> vertexList = new ArrayList<>();
		for (int i = 0; i < vertexCount; i++) {
			Vertex vertex = new Vertex(Integer.toString(i));
			graph.addVertex(vertex);
			vertexList.add(vertex);
		}

		if(edgeCount > 0){
			Random rNG = new Random();
			IntStream vertexNumberStream = rNG.ints(edgeCount*3, 0, vertexCount);
			PrimitiveIterator.OfInt vertexNumberIterator = vertexNumberStream.iterator();
	
			List<Vertex> unevenDegreeList = new ArrayList<>();
			List<Vertex> joinedVertexList = new ArrayList<>();
	
			joinedVertexList.add(new Vertex("0")); //This isn't completly unprejudiced
	
			Vertex sourceVertex;
	
			//while not all vertices are connected we have to make sure that all edges stay connected with eachother.
			//edgeCount-2 so we can make sure, that the structure is correct
			int addedEdgeCount = 0;
			for(; joinedVertexList.size() < vertexList.size() && addedEdgeCount < edgeCount - 2; addedEdgeCount++){
	
				sourceVertex = joinedVertexList.get(rNG.nextInt(joinedVertexList.size()));
				Vertex targetVertex = addEdgeToEulerGraph(rNG, vertexNumberIterator, graph, unevenDegreeList, sourceVertex, vertexList);
	
				if(!joinedVertexList.contains(targetVertex))
					joinedVertexList.add(targetVertex);
			}
	
			//When all vertices are connected we can choose freely
			for (; addedEdgeCount < edgeCount - 2; addedEdgeCount++) {
				sourceVertex = vertexList.get(vertexNumberIterator.nextInt());
				addEdgeToEulerGraph(rNG, vertexNumberIterator, graph, unevenDegreeList, sourceVertex, vertexList);
			}
	
			switch(unevenDegreeList.size()){
				case(0): //The graph already is an eulergraph but we still need to insert 2 additional edges
					Vertex randomVertex1 = vertexList.get(rNG.nextInt(vertexList.size()));
					Vertex randomVertex2 = vertexList.get(rNG.nextInt(vertexList.size()));
					graph.addEdge(randomVertex1, randomVertex2);
					graph.addEdge(randomVertex2, randomVertex1);
					break;
				case(2): //The graph is not an eulergraph but it contains an eulerpath, we only need to connect the loose ends via an intermediate
					Vertex middleVertex = vertexList.get(rNG.nextInt(vertexList.size()));
					graph.addEdge(unevenDegreeList.remove(0), middleVertex);
					graph.addEdge(middleVertex, unevenDegreeList.remove(0));
					break;
				case(4): //The graph has no eulerpath but all we need to do is connect the 4 lose ends to create an eulertour and therefore finish the eulergraph
					graph.addEdge(unevenDegreeList.remove(0), unevenDegreeList.remove(0));
					graph.addEdge(unevenDegreeList.remove(0), unevenDegreeList.remove(0));
					break;
				default: //Debug usage only, should be properly tested separately
					throw new IllegalStateException("The generated graph didn't conform to its rules, the generation Implementation is faulty.");
			}
		}
	}

	private Vertex addEdgeToEulerGraph(Random rNG, PrimitiveIterator.OfInt vertexNumberIterator, FlexibleGraph<Vertex, Edge> graph, 
										List<Vertex> unevenDegreeList, Vertex sourceVertex, List<Vertex> vertexList){
		Vertex targetVertex;
		if(unevenDegreeList.size() > 2){
			targetVertex = unevenDegreeList.get(rNG.nextInt(unevenDegreeList.size()));
		} else {
			targetVertex = vertexList.get(vertexNumberIterator.nextInt());
		}
		graph.addEdge(sourceVertex, targetVertex);
		updateDegreeList(unevenDegreeList, sourceVertex, targetVertex);
		return targetVertex;
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
		if(!sourceVertex.equals(targetVertex)){
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
}