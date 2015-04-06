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

	private static String graphOptionsLine = "(#directed)?\\s*(#attributed)?\\s*(#weigthed)?";
	private static String directed = "#directed";
	private static String attributed = "#attributed";
	private static String weighted = "#weighted";
	private boolean attributedGraph = false;

	public void parse(String file) {
		// TODO finish parser

		GraphReader fileReader = new GraphReader();
		List<String> lines = null;
		try {
			lines = fileReader.readFile(file);
		} catch (FileFormatException e) {
			// TODO: handle exception
		}
		String optionsLine = lines.get(0);
		
		attributedGraph = optionsLine.contains(attributed);
		if (optionsLine.contains(directed)) {
			if (optionsLine.contains(weighted)) {
				Graph<Vertex, DefaultWeightedEdge> graph = new DirectedWeightedMultigraph<Vertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
			}else {
				Graph<Vertex, DefaultEdge> graph = new DirectedMultigraph<Vertex, DefaultEdge>(DefaultEdge.class);
			}
		}else if(optionsLine.contains(weighted)){
			Graph<Vertex, DefaultWeightedEdge> graph = new WeightedMultigraph<Vertex, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		}else{
			Graph<Vertex, DefaultEdge> graph = new Multigraph<Vertex, DefaultEdge>(DefaultEdge.class);
		}
	}

}
