package lennart.magnus.borchert.GraphFramework.FileIO;

import java.util.List;

import lennart.magnus.borchert.GraphFramework.Materialien.Vertex;

import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedPseudograph;
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

	public DirectedPseudograph<Vertex, DefaultWeightedEdge> parse(String file) throws FileFormatException {
	//public DirectedWeightedMultigraph<Vertex, DefaultWeightedEdge> parse(String file) throws FileFormatException {
		// TODO test parser

		GraphReader fileReader = new GraphReader();
		List<String> lines = null;
		try {
			lines = fileReader.readFile(file);
		} catch (FileFormatException e) {
			throw e;
		}
		int i = 0;
		String optionsLine = lines.get(i);

		DirectedPseudograph<Vertex, DefaultWeightedEdge> graph = new DirectedPseudograph<>(DefaultWeightedEdge.class);
		//DirectedWeightedMultigraph<Vertex, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<>(DefaultWeightedEdge.class);
		directedGraph = optionsLine.contains(directed);
		attributedGraph = optionsLine.contains(attributed);
		weightedGraph = optionsLine.contains(weighted);

		if (directedGraph || weightedGraph || attributedGraph) {//If the first line isn't already defining the Graph we don't need to read it again.
			i++;
		}
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
