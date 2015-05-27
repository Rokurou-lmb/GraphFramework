package lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.Tests;

import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder.NextEdgeFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.EdgeFinder.PriorityQueueEdgeFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.Kruskal;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.Prim;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder.RandomVertexFinder;
import lennart.magnus.borchert.GraphFramework.Algorithms.MinimalSpanningTree.VertexFinder.VertexFinder;
import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.FlexibleGraph;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;
import lennart.magnus.borchert.GraphFramework.Tools.WeightedGraphTools;
import org.jgrapht.Graph;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by Gery on 27.05.2015.
 */
public class Kruskal_T {

    private String _path;
    private GraphParser _parser;
    private FlexibleGraph<Vertex, Edge> _graph;
    private Kruskal _kruskal;

    @Before
    public void setUp(){
        _parser = new GraphParser();
        try {
            _path = new File(".").getCanonicalPath()+"\\graphs\\junittest3.graph";
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
    }

    @Test
    public void test(){
        WeightedGraphTools<Vertex, Edge> graphTools = new WeightedGraphTools<>();
        assert(_graph.vertexSet().size() == 4);
        assert(_graph.edgeSet().size() == 6);

        Graph<Vertex, Edge> spanningTree = _kruskal.getMinimumSpanningTree();

        assert(spanningTree.vertexSet().containsAll(_graph.vertexSet()));
        assert(graphTools.getEdgeWeightSum(spanningTree) == 6);

    }
}
