package lennart.magnus.borchert.GraphFramework.Materialien;

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
	
	public boolean isDirected(){
		return _directed;
	}
	
	public boolean isWeighted(){
		return _weighted;
	}
}
