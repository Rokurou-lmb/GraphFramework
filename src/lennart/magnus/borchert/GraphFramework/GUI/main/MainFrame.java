package lennart.magnus.borchert.GraphFramework.GUI.main;


import lennart.magnus.borchert.GraphFramework.Algorithms.AStarShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.BreadthFirstSearchShortestPath;
import lennart.magnus.borchert.GraphFramework.Algorithms.DijkstraShortestPath;
import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Edge;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import org.jgraph.graph.DefaultGraphCell;
import org.jgrapht.Graph;

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
                        BreadthFirstSearchShortestPath bfs = new BreadthFirstSearchShortestPath();
                        if(getTwoSelected()) {
                            System.out.println(bfs.findShortestPath(graph, start, end));
                        }
                        break;
                    case "dij":
                        //TODO START DIJKSTRA
                        System.out.println("dijkstra start");
                        DijkstraShortestPath dij = new DijkstraShortestPath();
                        if(getTwoSelected()){
                            List path = dij.findShortestPath(graph,start,end);
                            if(path.size()==0) {
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "Kein Weg gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            String message = "";
                            for (int i = path.size()-1; i>=0; i--){
                                message += path.get(i)+" ";
                            }
                            JOptionPane.showMessageDialog(this._ui.getFrame(), "der weg ist: "+message, "Erfolg", JOptionPane.PLAIN_MESSAGE);
                        }
                        break;
                    case "as":
                        //TODO START A*
                        System.out.println("A* start");
                        AStarShortestPath as = new AStarShortestPath();
                        if(getTwoSelected()){
                            List path = as.findShortestPath(graph,start,end);
                            if(path.size()==0) {
                                JOptionPane.showMessageDialog(this._ui.getFrame(), "Kein Weg gefunden", "Fehler", JOptionPane.ERROR_MESSAGE);
                                break;
                            }
                            String message = "";
                            for (int i = path.size()-1; i>=0; i--){
                                message += path.get(i)+" ";
                            }
                            JOptionPane.showMessageDialog(this._ui.getFrame(), "der weg ist: "+message, "Erfolg", JOptionPane.PLAIN_MESSAGE);
                        }
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
            start = (Vertex)((DefaultGraphCell)selectedElements[0]).getUserObject();
            end = (Vertex)((DefaultGraphCell)selectedElements[1]).getUserObject();
            System.out.println(start.getIdentifier());
            System.out.println(end.getIdentifier());
            return true;
        } else {
            JOptionPane.showMessageDialog(this._ui.getFrame(), "Es kann nur der kürzeste Pfad zwischen zwei Knoten berechnet werden. Bitte wählen Sie genau zwei Knoten aus (Strg+Mausklick).", "Fehler", JOptionPane.ERROR_MESSAGE);
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