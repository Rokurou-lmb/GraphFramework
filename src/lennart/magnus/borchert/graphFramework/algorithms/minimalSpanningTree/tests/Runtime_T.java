package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.tests;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.Kruskal;
import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.MinimalSpanningTreeAlgorithm;
import lennart.magnus.borchert.graphFramework.materials.Edge;
import lennart.magnus.borchert.graphFramework.materials.FlexibleGraph;
import lennart.magnus.borchert.graphFramework.materials.Vertex;
import lennart.magnus.borchert.graphFramework.tools.GraphGenerator;

/**
 * Created by Gery on 28.05.2015.
 */
public class Runtime_T {

    private FlexibleGraph<Vertex, Edge> _graph;
    private MinimalSpanningTreeAlgorithm _kruskal;
    private MinimalSpanningTreeAlgorithm _prim;
    private Graph<Vertex, Edge> _spanningTree;
    private GraphGenerator _generator;
    private final int RNDMULT = 300000;
    private int randomVertex,randomEdge;
    private long time;

    @Before
    public void setUp(){
        _generator = new GraphGenerator();
        randomEdge = RNDMULT*2;
        randomVertex = RNDMULT;
        _graph = _generator.generateUndirectedWeightedGraph(Edge.class, randomVertex, randomEdge, 100);
        time = System.currentTimeMillis();
        _kruskal = new Kruskal(_graph);
        _spanningTree = _kruskal.createMinimalSpanningTree(_graph,Edge.class);
        time = System.currentTimeMillis()-time;
        System.err.println(time/1000);
    }

    @Test
    public void testGraphWasReadSuccessfully(){
        assert(_graph.vertexSet().size() == randomVertex);
        assert(_graph.edgeSet().size() == randomEdge);
    }

    @Test
    public void testSpanningTreeContainsAllVertices(){
        assert(_spanningTree.vertexSet().containsAll(_graph.vertexSet()));
    }

    @Test
    public void testSpanningTreeKruskalTime10(){
        assert(time<10*60000);
    }

    @Test
    public void testSpanningTreeKruskalTime5(){
        assert(time<5*60000);
    }

    @Test
    public void testSpanningTreeKruskalTime1(){
        assert(time<1*60000);
    }

}
