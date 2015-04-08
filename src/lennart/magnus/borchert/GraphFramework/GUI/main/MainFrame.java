package lennart.magnus.borchert.GraphFramework.GUI.main;


import lennart.magnus.borchert.GraphFramework.GUI.main.listener.Listener;

import java.io.File;

public class MainFrame {

    private MainFrameUI _ui;

    private FileChooser fc;

    private GraphDisplayer gd;

    public MainFrame(){
        String dir = "D:\\GKAP\\GraphFramework\\graphs";
        fc = new FileChooser(getFiles(dir));
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
        //gd.paintGraph(graph);
    }


}