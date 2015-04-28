package lennart.magnus.borchert.GraphFramework.Materials;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge {

	@Override
	public String toString(){
		return Double.toString(super.getWeight());
	}
}
