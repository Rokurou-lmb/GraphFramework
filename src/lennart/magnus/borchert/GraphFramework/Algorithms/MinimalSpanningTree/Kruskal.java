package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import org.jgrapht.Graph;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gery on 26.05.2015.
 */
public class Kruskal {

    private Graph _graph;

    public Kruskal(Graph graph) {
        _graph = graph;
    }

    public Set<Edge> createSpanningTree(){
        Set<Edge> result = new HashSet<>();
        Set<Vertex> visited = new HashSet<>();
        Set<Edge> remainingEdges = new HashSet<>(_graph.edgeSet());

        while (remainingEdges.size()>0){
            Edge e = findBestEdge(remainingEdges,visited);
            if(e == null){
                break;
            }
            Vertex s =(Vertex) _graph.getEdgeSource(e);
            Vertex t =(Vertex) _graph.getEdgeTarget(e);
            visited.add(s);
            visited.add(t);
            result.add(e);
            remainingEdges.remove(e);
        }

        return result;
    }

    private Edge findBestEdge(Set<Edge> edgeSet,Set<Vertex> fillGraph){
        //@TODO circle check not good results in 2 indipendent graphs maybe just connect them ?
        //find all minimal spanning trees and then connect them if there is no way to get to them
        Edge minEdge = null;
        for (Edge e : edgeSet){
            if(minEdge==null&&
                    (!(fillGraph.contains(_graph.getEdgeSource(e))&&
                            fillGraph.contains(_graph.getEdgeTarget(e))))){
                minEdge = e;
            }else if (_graph.getEdgeWeight(minEdge)>_graph.getEdgeWeight(e)&&
                    (!(fillGraph.contains(_graph.getEdgeSource(e))&&
                            fillGraph.contains(_graph.getEdgeTarget(e))))){
                minEdge = e;
            }
        }
        return minEdge;
    }
}
