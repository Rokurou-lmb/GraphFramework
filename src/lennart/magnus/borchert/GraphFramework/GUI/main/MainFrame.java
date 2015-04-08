package lennart.magnus.borchert.GraphFramework.GUI.main;


import lennart.magnus.borchert.GraphFramework.GUI.main.listener.Listener;

import java.io.File;

public class MainFrame {

    private MainFrameUI _ui;

    private FileChooser fc;

    private GraphDisplayer gd;

    public MainFrame(){
        String dir = "D:\\GKAP\\GraphFramework";
        fc = new FileChooser(getFiles(dir));
        fc.addListener(() -> {
            updateGraph();
        });
        gd = new GraphDisplayer();
        _ui = new MainFrameUI(fc.getUI(),gd.getUI());
    }

    private String[] getFiles(String path){
        File dir = new File(path);
        String[] files = new String[dir.listFiles().length];
        int i = 0;
        for(File f : dir.listFiles()){
            files[i++] = f.toString();
        }
        return files;
    }

    private void updateGraph(){
        //TODO use reader/parser to get a graph from file
        //gd.paintGraph(graph);
    }


}