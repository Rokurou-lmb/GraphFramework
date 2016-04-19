package lennart.magnus.borchert.graphFramework.gui;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jgraph.graph.DefaultGraphCell;
import org.jgrapht.Graph;

import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.Kruskal;
import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.Prim;
import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.edgeFinder.PriorityQueueEdgeFinder;
import lennart.magnus.borchert.graphFramework.algorithms.minimalSpanningTree.vertexFinder.RandomVertexFinder;
import lennart.magnus.borchert.graphFramework.algorithms.shortestPath.AStarShortestPath;
import lennart.magnus.borchert.graphFramework.algorithms.shortestPath.BreadthFirstSearchShortestPath;
import lennart.magnus.borchert.graphFramework.algorithms.shortestPath.DijkstraShortestPath;
import lennart.magnus.borchert.graphFramework.fileIO.FileFormatException;
import lennart.magnus.borchert.graphFramework.fileIO.GraphParser;
import lennart.magnus.borchert.graphFramework.materials.Edge;
import lennart.magnus.borchert.graphFramework.materials.Vertex;
import lennart.magnus.borchert.graphFramework.tools.WeightedGraphTools;

public class MainFrame {

    private String DIR = "";

    private MainFrameUI _ui;

    private FileChooser fc;

    private GraphDisplayer gd;

    private GraphParser parser;
    
    private Graph<Vertex, Edge> graph;

    private Vertex start,end;

    public MainFrame(){
        try {
            DIR = new File(".").getCanonicalPath()+"\\graphs";
        } catch (IOException e) {
            e.printStackTrace();
        }

        parser = new GraphParser();

        fc = new FileChooser(getFiles(DIR));
        fc.addListener(s -> {

                switch (s){
                    default:
                        updateGraph();
                        break;
                    case "bfs":
                        //TODO START BFS
                        System.out.println("bfs start");
                        BreadthFirstSearchShortestPath<Vertex, Edge> bfs = new BreadthFirstSearchShortestPath<>();

                        if(getTwoSelected()) {
                            List<Vertex> path = bfs.findShortestPath(graph, start, end);
                            if(path.size()>0){
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "der weg ist: "+path, "Erfolg", JOptionPane.PLAIN_MESSAGE);
                            }else {
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "Kein Weg gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "dij":
                        //TODO START DIJKSTRA
                        System.out.println("dijkstra start");
                        DijkstraShortestPath<Vertex, Edge> dij = new DijkstraShortestPath<>();
                        if(getTwoSelected()){
                            List<Vertex> path = dij.findShortestPath(graph,start,end);
                            if(path.size()>0){
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "der weg ist: "+path, "Erfolg", JOptionPane.PLAIN_MESSAGE);
                            }else {
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "Kein Weg gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "as":
                        //TODO START A*
                        System.out.println("A* start");
                        AStarShortestPath<Vertex, Edge> as = new AStarShortestPath<>();
                        if(getTwoSelected()){
                            List<Vertex> path = as.findShortestPath(graph,start,end);
                            if(path.size()>0){
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "der weg ist: "+path, "Erfolg", JOptionPane.PLAIN_MESSAGE);
                            }else {
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "Kein Weg gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        break;
                    case "kruskal":
                        System.out.println("Kruskal start");
                        Kruskal k = new Kruskal(this.graph);
                        String msg = "";
                        for (Edge e : k.getMinimumSpanningTreeEdgeSet()){
                            msg += "("+(graph.getEdgeSource(e)+","+graph.getEdgeTarget(e))+")";
                        }
                        JOptionPane.showMessageDialog(this._ui.getFrame(), "Der minimale Spannbaum hat volgende Kanten: "+msg+"\nSumme der Kantengewichte: "+k.getMinimumSpanningTreeTotalWeight(), "Erfolg", JOptionPane.PLAIN_MESSAGE);
                        break;
                    case "prim":
                        System.out.println("Prim start");
                        Prim p = new Prim<>(new PriorityQueueEdgeFinder(graph),new RandomVertexFinder(graph));
                        Graph minSpan = p.createMinimalSpanningTree(graph,Edge.class);
                        String message = "";
                        for (Edge e : (Set<Edge>)minSpan.edgeSet()){
                            message += "("+(graph.getEdgeSource(e)+","+graph.getEdgeTarget(e))+")";
                        }
                        JOptionPane.showMessageDialog(this._ui.getFrame(), "Der minimale Spannbaum hat volgende Kanten: "+message+"\nSumme der Kantengewichte: "+new WeightedGraphTools<Vertex,Edge>().getEdgeWeightSum(minSpan), "Erfolg", JOptionPane.PLAIN_MESSAGE);
                        break;
                }


        });
        gd = new GraphDisplayer();
        
        JButton suchButton = new JButton("suche");
        suchButton.addActionListener(e -> {

        });
        
        _ui = new MainFrameUI(fc.getUI(),gd.getUI(),suchButton);
    }

    private boolean getTwoSelected() {
        Object[] selectedElements = gd.getSelectedNodes();
        if(selectedElements.length == 2) {
            try{
                start = (Vertex)((DefaultGraphCell)selectedElements[0]).getUserObject();
                end = (Vertex)((DefaultGraphCell)selectedElements[1]).getUserObject();
                System.out.println(start.getIdentifier());
                System.out.println(end.getIdentifier());
                return true;
            }catch (ClassCastException e){
                JOptionPane.showMessageDialog(this._ui.getFrame(), "Bitte keine Kanten auswaehlen.", "Fehler", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this._ui.getFrame(), "Es kann nur der kuerzeste Pfad zwischen zwei Knoten berechnet werden.", "Fehler", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    private String[] getFiles(String path){
        File dir = new File(path);
        String[] files = new String[dir.listFiles().length+1];
        //files[0] = "--choose--";
        int i = 1;
        for(File f : dir.listFiles()){
            files[i++] = f.getName();
        }
        return files;
    }

    private void updateGraph(){
        //TODO use reader/parser to get a graph from file
        try {
        	graph = parser.parse(DIR+"\\"+fc.getSelectedFile());
            gd.paintGraph(graph);
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }


}