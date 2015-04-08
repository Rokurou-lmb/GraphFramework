package lennart.magnus.borchert.GraphFramework.GUI.main;


import lennart.magnus.borchert.GraphFramework.FileIO.FileFormatException;
import lennart.magnus.borchert.GraphFramework.FileIO.GraphParser;
import lennart.magnus.borchert.GraphFramework.GUI.main.listener.Listener;

import java.io.File;

public class MainFrame {

    private static String DIR = "D:\\GKAP\\GraphFramework\\graphs";

    private MainFrameUI _ui;

    private FileChooser fc;

    private GraphDisplayer gd;

    private GraphParser parser;

    public MainFrame(){
        parser = new GraphParser();

        fc = new FileChooser(getFiles(DIR));
        fc.addListener(() -> {
            updateGraph();
            System.out.println(fc.getSelectedFile());
        });
        gd = new GraphDisplayer();
        _ui = new MainFrameUI(fc.getUI(),gd.getUI());
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
            gd.paintGraph(parser.parse(DIR+"\\"+fc.getSelectedFile()));
        } catch (FileFormatException e) {
            e.printStackTrace();
        }
    }


}