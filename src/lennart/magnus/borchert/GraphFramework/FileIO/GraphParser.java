package lennart.magnus.borchert.GraphFramework.FileIO;

import java.util.List;

import lennart.magnus.borchert.GraphFramework.Materialien.Vertex;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedMultigraph;

/**
 * 
 * @author Lenno
 *
 */
public class GraphParser {

	private static String directed = "#directed";
	private static String attributed = "#attributed";
	private static String weighted = "#weighted";
	private boolean directedGraph = false;
	private boolean attributedGraph = false;
	private boolean weightedGraph = false;

	public DirectedWeightedMultigraph<Vertex, DefaultWeightedEdge> parse(String file) {
		// TODO test parser

		GraphReader fileReader = new GraphReader();
		List<String> lines = null;
		try {
			lines = fileReader.readFile(file);
		} catch (FileFormatException e) {
			// TODO: handle exception
		}
		int i = 0;
		String optionsLine = lines.get(i);

		DirectedWeightedMultigraph<Vertex, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		directedGraph = optionsLine.contains(directed);
		attributedGraph = optionsLine.contains(attributed);
		weightedGraph = optionsLine.contains(weighted);
		DefaultWeightedEdge edge;
		DefaultWeightedEdge invertedEdge;

		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] vertices = line.split(",");
			String[] sourceValues = vertices[0].split(":");
			String[] targetValues = vertices[1].split(":");
			Vertex source;
			Vertex target;

			if (attributedGraph) {
				source = new Vertex(sourceValues[0], Integer.parseInt(sourceValues[1]));
				target = new Vertex(targetValues[0], Integer.parseInt(targetValues[1]));
			} else {
				source = new Vertex(sourceValues[0], 0);
				target = new Vertex(targetValues[0], 0);
			}

			graph.addVertex(source);
			graph.addVertex(target);
			edge = new DefaultWeightedEdge();
			invertedEdge = new DefaultWeightedEdge();
			if (weightedGraph) {
				graph.setEdgeWeight(edge, Integer.parseInt(targetValues[targetValues.length-1]));
				graph.setEdgeWeight(invertedEdge, Integer.parseInt(targetValues[targetValues.length-1]));
			}

			graph.addEdge(source, target, edge);
			if (!directedGraph) {
				graph.addEdge(target, source, invertedEdge);
			}
		}
		return graph;
	}
}
