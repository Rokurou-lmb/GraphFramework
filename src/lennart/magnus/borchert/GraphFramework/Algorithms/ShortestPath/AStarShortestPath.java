package lennart.magnus.borchert.GraphFramework.Algorithms.ShortestPath;

import java.lang.reflect.Array;
import java.util.*;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import org.jgrapht.Graph;

public class AStarShortestPath<V, E> extends AbstractShortestPathAlgorithm<V, E>{

	private int _counter = 0;

	public int getCounter(){
		return _counter;
	}

	@Override
	protected List<V> shortestPathHelper(Graph<V, E> graph, V startVertex, V endVertex) {
		List<V> path = new ArrayList<V>();

		Vertex start = (Vertex)startVertex;
		Vertex end = (Vertex)endVertex;

		HashMap<V,AStarData> aStarTable = new HashMap<>();
		//put start Vertex
		aStarTable.put(startVertex, new AStarData(start.getAttribute(), 0));
		((AStarData)aStarTable.values().toArray()[0]).setPre((Vertex)startVertex);
		//put all Vertexes
		for(V vertex : graph.vertexSet()){
			_counter++;
			if(!aStarTable.keySet().contains(vertex))
				aStarTable.put(vertex,new AStarData(((Vertex) vertex).getAttribute()));
		}

		Set<E> currentEdges;
		V targetVertex;

		//find Vertex with lowest f

		Map.Entry<V,AStarData> prefData = findBest(aStarTable);
		while(prefData!=null&&!aStarTable.get(endVertex).isOk()){
			prefData.getValue().setOk(true);

			//get next set with !Ok
			currentEdges = ((FlexibleGraph<V, E>)graph).getOutgoingEdges(prefData.getKey());
			for (E edge : currentEdges) {
				if(!((FlexibleGraph<V, E>) graph).getEdgeTarget(edge).toString().equals(prefData.getKey().toString())){
					_counter++;
					targetVertex = graph.getEdgeTarget(edge);
					//System.out.print("to " + targetVertex.toString());
					AStarData mnext = aStarTable.get(targetVertex);
					double d = 1;
					if(((Edge)edge).toString().length()>0)
						d = Double.valueOf(((Edge)edge).toString());
					//System.out.println(" -> "+d);
					//update data
					if(!mnext.isOk()&&mnext.getD()>d+prefData.getValue().getD()){
						mnext.setPre((Vertex)prefData.getKey());
						mnext.setD(d+prefData.getValue().getD());
					}
				}
			}

			//find next best
			//System.out.println(prefData.getKey().toString()+": ok:"+prefData.getValue().isOk()+"|d:"+prefData.getValue().getD()+"|f"+prefData.getValue().getF());//"|pref:"+prefData.getValue().getPre().toString());
			prefData = findBest(aStarTable);
		}

		//check if target reached
		if(aStarTable.size()>0&&aStarTable.get(endVertex).isOk()){
			//find way back and add to path
			AStarData data = aStarTable.get(endVertex);
			path.add(0,endVertex);
			while(aStarTable.get(startVertex).isOk()){
				if(data.getPre()==startVertex){
					if(!path.contains(startVertex))path.add(0,startVertex);
					aStarTable.get(startVertex).setOk(false);
				} else {
					path.add(0,(V)data.getPre());
					data = aStarTable.get(data.getPre());
				}
			}
		}
		//System.out.println(_counter);
		return path;
	}

	private Map.Entry<V,AStarData> findBest(Map<V,AStarData> map){
		Map.Entry<V,AStarData> result = null;

		for(Map.Entry<V,AStarData> data : map.entrySet()){
			if(data.getValue().getF()<Integer.MAX_VALUE&&!data.getValue().isOk()) {
				if(result==null){
					result=data;
				}else if(data.getValue().getF()<result.getValue().getF()){
					result=data;
				}
			}
		}
		//if(result!=null)System.out.println("select " + result.getKey().toString());
		return result;
	}

	private class AStarData{
		private boolean _ok;
		private int _h;
		private double _d;
		private double _f;
		private Vertex _pre;

		public AStarData(int h){
			_h = h;
			_ok = false;
			_d = Integer.MAX_VALUE;
			_f = _d;
			_pre = null;
		}

		public AStarData(int h,double d){
			this(h);
			_d = d;
			_f = d+h;
		}

		public double getD(){
			return _d;
		}

		public double getF(){
			return _f;
		}

		public Vertex getPre(){
			return _pre;
		}

		public boolean isOk(){
			return _ok;
		}

		public void setOk(boolean ok){
			_ok = ok;
		}

		public void setD(double d){
			_d = d;
			_f = _h + d;
		}

		public void setPre(Vertex pre){
			_pre = pre;
		}
	}
}