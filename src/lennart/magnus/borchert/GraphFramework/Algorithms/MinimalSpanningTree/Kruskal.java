package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree;

import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MinimumSpanningTree;
import org.jgrapht.alg.util.UnionFind;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Gery on 26.05.2015.
 */
public class Kruskal implements MinimumSpanningTree{

    private Graph<Vertex,Edge> graph;
    private Set<Edge> edgeSet;
    private double totalWeight;

    public Kruskal(Graph graph) {
        this.graph = graph;
        edgeSet = new HashSet<>();
        UnionFind<Vertex> vertexes = new UnionFind<Vertex>(graph.vertexSet());
        Set<Edge> remainingEdges = new HashSet<>(graph.edgeSet());

        while (remainingEdges.size()>0){
            Edge e = findBestEdge(remainingEdges,graph);


            Vertex s =(Vertex) graph.getEdgeSource(e);
            Vertex t =(Vertex) graph.getEdgeTarget(e);
            remainingEdges.remove(e);
            if (!vertexes.find(s).equals(vertexes.find(t))) {
                vertexes.union(s, t);
                edgeSet.add(e);
                totalWeight += graph.getEdgeWeight(e);
            }
        }
    }

    private Edge findBestEdge(Set<Edge> edges,Graph graph){
        Edge minEdge = null;
        for (Edge e : edges){
            if(minEdge==null) minEdge = e;
            if (graph.getEdgeWeight(e)<graph.getEdgeWeight(minEdge)){
                minEdge = e;
            }
        }
        return minEdge;
    }

    @Override
    public Set<Edge> getMinimumSpanningTreeEdgeSet() {
        return edgeSet;
    }

    @Override
    public double getMinimumSpanningTreeTotalWeight() {
        return totalWeight;
    }

    public Graph<Vertex, Edge> getMinimumSpanningTree() {
        Graph result = new FlexibleGraph<>(false,true,Edge.class);
        for (Edge e : this.edgeSet){
            Vertex s = graph.getEdgeSource(e);
            Vertex t = graph.getEdgeTarget(e);
            result.addVertex(s);
            result.addVertex(t);
            result.addEdge(s,t,e);
        }
        return result;
    }
}
