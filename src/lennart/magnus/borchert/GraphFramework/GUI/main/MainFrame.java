package lennart.magnus.borchert.GraphFramework.GUI.main;


import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.Materials.Vertex;

import java.io.File;
import java.io.IOException;

import javax.swing.JButton;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class MainFrame {

    private String DIR = "";

    private MainFrameUI _ui;

    private FileChooser fc;

    private GraphDisplayer gd;

    private GraphParser parser;
    
    private Graph<Vertex, DefaultWeightedEdge> graph;

    public MainFrame(){
        try {
            DIR = new File(".").getCanonicalPath()+"\\graphs";
        } catch (IOException e) {
            e.printStackTrace();
        }

        parser = new GraphParser();

        fc = new FileChooser(getFiles(DIR));
        fc.addListener(() -> {
            updateGraph();
            System.out.println(fc.getSelectedFile());
        });
        gd = new GraphDisplayer();
        
        JButton suchButton = new JButton("suche");
        suchButton.addActionListener(e -> {
        	
        });
        
        _ui = new MainFrameUI(fc.getUI(),gd.getUI(),suchButton);
    }

    private String[] getFiles(String path){
        File dir = new File(path);
        String[] files = new String[dir.listFiles().length+1];
        files[0] = "--choose--";
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