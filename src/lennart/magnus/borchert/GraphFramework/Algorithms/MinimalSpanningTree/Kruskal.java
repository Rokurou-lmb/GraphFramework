package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.UnionFind;
import org.jgrapht.graph.DefaultWeightedEdge;

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
        UnionFind<Vertex> visited = new UnionFind<Vertex>(_graph.vertexSet());
        Set<Edge> remainingEdges = new HashSet<>(_graph.edgeSet());

        while (remainingEdges.size()>0){
            Edge e = findBestEdge(remainingEdges);


            Vertex s =(Vertex) _graph.getEdgeSource(e);
            Vertex t =(Vertex) _graph.getEdgeTarget(e);
            remainingEdges.remove(e);
            if (!visited.find(s).equals(visited.find(t))) {
                visited.union(s, t);
                result.add(e);
            }
        }

        return result;
    }

    private Edge findBestEdge(Set<Edge> edges){
        Edge minEdge = null;
        for (Edge e : edges){
            if(minEdge==null) minEdge = e;
            if (_graph.getEdgeWeight(e)<_graph.getEdgeWeight(minEdge)){
                minEdge = e;
            }
        }
        return minEdge;
    }
}
