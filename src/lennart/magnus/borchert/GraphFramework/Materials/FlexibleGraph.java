package lennart.magnus.borchert.GraphFramework.Materials;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

//TODO rework needed, extend undirected and implement directed functionality 
public class FlexibleGraph<V, E> extends DirectedWeightedPseudograph<V, E> implements Graph<V, E> {


	private static final long	serialVersionUID	= 1L; //Default SerialUID
	private boolean _directed;
	private boolean _weighted;


	public FlexibleGraph(boolean directed, boolean weighted, Class<? extends E> edgeClass){
		super(edgeClass);
		_directed = directed;
		_weighted = weighted;
	}
	
	public FlexibleGraph(FlexibleGraph<V, E> graph, Class<? extends E> edgeClass){
		super(edgeClass);
		_directed = graph.isDirected();
		_weighted = graph.isWeighted();
		for (V vertex : graph.vertexSet()) {
			addVertex(vertex);
		}
		for (E edge : graph.edgeSet()) {
			addEdge(graph.getEdgeSource(edge), graph.getEdgeTarget(edge), edge);
			graph.setEdgeWeight(edge, graph.getEdgeWeight(edge));
		}

	}

	@Override
	public E getEdge(V sourceVertex, V targetVertex) {
		return super.getEdge(sourceVertex, targetVertex);
	}

	@Override
	public double getEdgeWeight(E e) {
		return super.getEdgeWeight(e);
	}

	/**
	 * Generates a Set of all Vertices
	 * 
	 * @return Set of all Vertices
	 */
	@Override
	public Set<V> vertexSet(){
		Set<V> vertexSet = new HashSet<>();
		vertexSet.addAll(super.vertexSet());//This somehow changes the GUI layout
		return vertexSet;
	}

	@Override
	public boolean containsEdge(V sourceVertex, V targetVertex) {
		if (_directed) {
			return super.containsEdge(sourceVertex, targetVertex);
		}else{
			return super.containsEdge(sourceVertex, targetVertex) || super.containsEdge(targetVertex, sourceVertex);
		}
	}

	/**
	 * Generates a Set of all outgoing Edges for the given Vertex
	 * 
	 * @param vertex for which to get outgoing Edges
	 * @return Set of all outgoing Edges
	 */
	public Set<E> getOutgoingEdges(V vertex){
		Set<E> outgoingEdges = new HashSet<>();
		outgoingEdges.addAll(super.edgesOf(vertex));
		if(_directed){ //If the graph is directed remove all incoming edges from the edgeSet
			Iterator<E> outgoingEdgeIterator = outgoingEdges.iterator();
			while(outgoingEdgeIterator.hasNext()){
				E edge = outgoingEdgeIterator.next();
				if(!getEdgeSource(edge).equals(vertex))
					outgoingEdgeIterator.remove();
			}			
		}
		return outgoingEdges;
	}

	/**
	 * Generates a Set of all incoming Edges for the given Vertex
	 * 
	 * @param vertex for which to get incoming Edges
	 * @return Set of all incoming Edges
	 */
	public Set<E> getIncomingEdges(V vertex){
		Set<E> incomingEdges = new HashSet<>();
		incomingEdges.addAll(super.edgesOf(vertex));
		if(_directed){ //If the graph is directed remove all outgoing edges from the edgeSet
			Iterator<E> incomingEdgeIterator = incomingEdges.iterator();
			while(incomingEdgeIterator.hasNext()){
				E edge = incomingEdgeIterator.next();
				if(!getEdgeTarget(edge).equals(vertex))
					incomingEdgeIterator.remove();
			}
		}
		return incomingEdges;
	}
	
	public boolean isDirected(){
		return _directed;
	}
	
	public boolean isWeighted(){
		return _weighted;
	}
}
