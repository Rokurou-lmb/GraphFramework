package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree;

import java.util.HashSet;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.MinimumSpanningTree;
import org.jgrapht.alg.util.UnionFind;

import lennart.magnus.borchert.graphFramework.materials.Edge;
import lennart.magnus.borchert.graphFramework.materials.FlexibleGraph;
import lennart.magnus.borchert.graphFramework.materials.Vertex;

/**
 * Created by Gery on 26.05.2015.
 */
public class Kruskal implements MinimumSpanningTree,MinimalSpanningTreeAlgorithm{

    private Set<Edge> edgeSet;
    private double totalWeight;

    public Kruskal(Graph graph) {
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

    @Override
    public Graph createMinimalSpanningTree(Graph graph, Class edgeClass) {
        Graph result = new FlexibleGraph<>(false,true,edgeClass);
        for (Edge e : this.edgeSet){
            Vertex s = (Vertex)graph.getEdgeSource(e);
            Vertex t = (Vertex)graph.getEdgeTarget(e);
            result.addVertex(s);
            result.addVertex(t);
            result.addEdge(s, t, e);
        }
        return result;
    }
}
