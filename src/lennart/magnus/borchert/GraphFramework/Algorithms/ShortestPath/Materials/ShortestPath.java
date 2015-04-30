package lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath.Materials;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

public class ShortestPath {
	private static Vertex _startVertex;
	private static Vertex _endVertex;
	private static List<Vertex> _path;
	private static Graph<Vertex, DefaultWeightedEdge> _graph;

	public ShortestPath(Graph<Vertex, DefaultWeightedEdge> graph, Vertex v1, Vertex v2, List<Vertex> path){
		_startVertex = v1;
		_endVertex = v2;
		_path = path;
		_graph = graph;
	}
	
	public Vertex getStartVertex(){
		return _startVertex;
	}
	
	public Vertex getEndVertex(){
		return _endVertex;
	}
	
	public Graph<Vertex, DefaultWeightedEdge> getGraph(){
		return _graph;
	}
	
	public List<Vertex> getPath(){
		return _path;
	}
}
