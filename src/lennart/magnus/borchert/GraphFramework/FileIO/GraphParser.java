package lennart.magnus.borchert.GraphFramework.FileIO;

import java.util.List;

import lennart.magnus.borchert.GraphFramework.Materialien.Vertex;

import org.jgraph.graph.DefaultEdge;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedMultigraph;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.Multigraph;
import org.jgrapht.graph.WeightedMultigraph;

/**
 * 
 * @author Lenno
 *
 */
public class GraphParser {

	private static String graphOptionsLine = "(#directed)?(#attributed)?(#weigthed)?";
	private static String directed = "#directed";
	private static String attributed = "#attributed";
	private static String weighted = "#weighted";
	private boolean attributedGraph = false;

	public Graph parse(String file) {
		// TODO finish parser
		Graph<Vertex, DefaultEdge> graph;
		
		GraphReader fileReader = new GraphReader();
		List<String> lines = null;
		try {
			lines = fileReader.readFile(file);
		} catch (FileFormatException e) {
			// TODO: handle exception
		}
		int i = 0;
		String optionsLine = lines.get(i);

		attributedGraph = optionsLine.contains(attributed);
		if (optionsLine.contains(directed)) {
			if (optionsLine.contains(weighted)) {
				Graph<Vertex, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<Vertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			}else {
				Graph<Vertex, DefaultEdge> graph = new DirectedMultigraph<Vertex, DefaultEdge>(DefaultEdge.class);
			}
			i++;
		}else if(optionsLine.contains(weighted)){
			Graph<Vertex, DefaultWeightedEdge> graph = new WeightedMultigraph<Vertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			i++;
		}else{
			Graph<Vertex, DefaultEdge> graph = new Multigraph<Vertex, DefaultEdge>(DefaultEdge.class);
		}
		for (; i < lines.size(); i++) {
			String line = lines.get(i);
			String[] vertices = line.split(",");

			String[] values = vertices[0].split(":");
			Vertex source = new Vertex(values[0], Integer.parseInt(values[1]));
			values = vertices[1].split(":");
			Vertex target = new Vertex(values[0], Integer.parseInt(values[1]));

			graph.addVertex(source);
		}
		return graph;
	}
}
