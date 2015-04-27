package lennart.magnus.borchert.GraphFramework.FileIO;

import java.util.List;

import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * 
 * @author Lenno
 *
 */
public class GraphParser {

	private static final String _DIRECTED = "#directed";
	private static final String _ATTRIBUTED = "#attributed";
	private static final String _WEIGHTED = "#weighted";

	public FlexibleGraph<Vertex, DefaultWeightedEdge> parse(String file) throws FileFormatException {
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

		boolean directedGraph = optionsLine.contains(_DIRECTED);
		boolean attributedGraph = optionsLine.contains(_ATTRIBUTED);
		boolean weightedGraph = optionsLine.contains(_WEIGHTED);
		FlexibleGraph<Vertex, DefaultWeightedEdge> graph = new FlexibleGraph<>(directedGraph,weightedGraph,DefaultWeightedEdge.class);
		
		if (directedGraph || weightedGraph || attributedGraph) {//If the first line isn't already defining the Graph we don't need to read it again.
			i++;
		}

		Vertex source;
		Vertex target;
		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] vertices = line.split(",");
			String[] sourceValues = vertices[0].split(":");
			String[] targetValues = vertices[1].split(":");

			DefaultWeightedEdge edge;
			
			//TODO: Secure this part against wrong format
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
			graph.addEdge(source, target, edge);
		}
		return graph;
	}
}
