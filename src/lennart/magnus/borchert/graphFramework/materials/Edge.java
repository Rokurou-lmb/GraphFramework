package lennart.magnus.borchert.graphFramework.materials;

import org.jgrapht.graph.DefaultWeightedEdge;

public class Edge extends DefaultWeightedEdge {

	/**
	 * Auto generated serialVersionUID.
	 */
	private static final long	serialVersionUID	= 8582751352761450312L;

	@Override
	public String toString(){
		if (super.getWeight() == 1.0)
			return "";

		return Double.toString(super.getWeight());
	}
}
