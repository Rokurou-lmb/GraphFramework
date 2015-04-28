package lennart.magnus.borchert.GraphFramework.Materials;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DirectedWeightedPseudograph;

public class FlexibleGraph<V, E> extends DirectedWeightedPseudograph<V, E> implements Graph<V, E> {


	private static final long	serialVersionUID	= 1L; //Default SerialUID
	private boolean _directed;
	private boolean _weighted;


	public FlexibleGraph(boolean directed, boolean weighted, Class<? extends E> edgeClass){
		super(edgeClass);
		_directed = directed;
		_weighted = weighted;
	}

	@Override
	public E getEdge(V sourceVertex, V targetVertex) {
	
		return super.getEdge(sourceVertex, targetVertex);
	}

	@Override
	public double getEdgeWeight(E e) {
		if (_weighted) {
			return super.getEdgeWeight(e);
		}else{
			return 1.0;
		}
	}

	@Override
	public boolean containsEdge(V sourceVertex, V targetVertex) {
		if (_directed) {
			return super.containsEdge(sourceVertex, targetVertex);		
		}else{
			return super.containsEdge(sourceVertex, targetVertex) || super.containsEdge(targetVertex, sourceVertex);
		}
	}
	
	public Set<E> getOutgoingEdges(V vertex){
		if(!_directed)
			return super.edgesOf(vertex);

		Set<E> outgoingEdges = super.edgesOf(vertex);
		Iterator<E> outgoingEdgeIterator = outgoingEdges.iterator();
		while(outgoingEdgeIterator.hasNext()){
			E edge = outgoingEdgeIterator.next();
			if(!getEdgeSource(edge).equals(vertex))
				outgoingEdgeIterator.remove();
			
		}
		return outgoingEdges;
	}
	
	public boolean isDirected(){
		return _directed;
	}
	
	public boolean isWeighted(){
		return _weighted;
	}
}
