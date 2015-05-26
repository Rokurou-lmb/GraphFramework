package lennart.magnus.borchert.GraphFramework.Tools;

import java.util.Iterator;
import java.util.Set;

import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;

import org.jgrapht.graph.DefaultWeightedEdge;

public class GraphTools<V, E extends DefaultWeightedEdge> {

	/**
	 * Returns the one Edge from sourceVertex to targetVertex with the lowest weight.
	 * 
	 * @param graph
	 * @param sourceVertex
	 * @param targetVertex
	 * @return The lightest Edge from sourceVertex to targetVertex
	 */
	public E findLightestEdge(FlexibleGraph<V,E> graph, V sourceVertex, V targetVertex){
		//TODO ensure safety by building suitable contract
		Set<E> edgeSet = graph.getAllEdges(sourceVertex, targetVertex);
		Iterator<E> edgeSetIterator = edgeSet.iterator();
		E lightestEdge = edgeSetIterator.next();
		E edge;
		while (edgeSetIterator.hasNext()) {
			edge = edgeSetIterator.next();
			if(graph.getEdgeWeight(lightestEdge) > graph.getEdgeWeight(edge))
				lightestEdge = edge;
		}
		return lightestEdge;
	}
}