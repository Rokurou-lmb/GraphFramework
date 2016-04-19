package lennart.magnus.borchert.graphFramework;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;

import lennart.magnus.borchert.graphFramework.gui.MainFrame;
import lennart.magnus.borchert.graphFramework.materials.Vertex;

public class GraphFramework {
	
	private DijkstraShortestPath<Vertex, DefaultWeightedEdge> _dijkstra;

	public static void main(String[] args){
		new MainFrame();
	}
	
	public List<DefaultWeightedEdge> findShortestPath(Graph<Vertex, DefaultWeightedEdge> graph, Vertex source, Vertex target){
		_dijkstra = new DijkstraShortestPath<Vertex, DefaultWeightedEdge>(graph, source, target);
		return _dijkstra.getPath().getEdgeList();
	}
}
