package lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.tests;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.Kruskal;
import lennart.magnus.borchert.graphFramework.fileIO.FileFormatException;
import lennart.magnus.borchert.graphFramework.fileIO.GraphParser;
import lennart.magnus.borchert.graphFramework.materials.Edge;
import lennart.magnus.borchert.graphFramework.materials.FlexibleGraph;
import lennart.magnus.borchert.graphFramework.materials.Vertex;
import lennart.magnus.borchert.graphFramework.tools.WeightedGraphTools;

/**
 * Created by Gery on 27.05.2015.
 */
public class Kruskal_T {

    private String _path;
    private GraphParser _parser;
    private FlexibleGraph<Vertex, Edge> _graph;
    private Kruskal _kruskal;
    private Graph<Vertex, Edge> _spanningTree;

    @Before
    public void setUp(){
        _parser = new GraphParser();
        try {
            _path = new File(".").getCanonicalPath()+"\\graphs\\junitTestGraphs\\junittest3.graph";
            try {
                _graph = _parser.parse(_path);
            } catch (FileFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        _kruskal = new Kruskal(_graph);
        _spanningTree = _kruskal.createMinimalSpanningTree(_graph,Edge.class);
    }

    @Test
    public void testGraphWasReadSuccessfully(){
        assert(_graph.vertexSet().size() == 4);
        assert(_graph.edgeSet().size() == 9);
    }

    @Test
    public void testSpanningTreeContainsAllVertices(){
        assert(_spanningTree.vertexSet().containsAll(_graph.vertexSet()));
    }

    @Test
    public void testSpanningTreeIsMinimal(){
        WeightedGraphTools<Vertex, Edge> graphTools = new WeightedGraphTools<>();
        assertEquals(6.0, graphTools.getEdgeWeightSum(_spanningTree), 0.1);
    }
}
