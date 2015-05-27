package lennart.magnus.borchert.GraphFramework.GUI.main;

import java.util.ArrayList;

import javax.swing.*;

import lennart.magnus.borchert.GraphFramework.GUI.main.listener.Talker;

/**
 * Created by Gery on 08.04.2015.
 */
public class FileChooser extends Talker{

    private FileChooserUI _ui;

    private String selectedFile = "none";

    public FileChooser(String[] files){
        //_ui = new FileChooserUI(files);
        //_ui.getCombo().addItemListener(e -> {
        //    updateSelected();
        //    talk();
        //});
        ArrayList<JMenuItem> items = new ArrayList<>();
        for(String file : files){
            if(file!=null){
                JMenuItem item = new JMenuItem(file);
                item.addActionListener(e -> {
                    selectedFile =((JMenuItem) e.getSource()).getText();
                    talk("");
                });
                items.add(item);
            }
        }
        JMenuItem bfsButton = new JMenuItem("BFS");
        bfsButton.addActionListener(e -> {
            talk("bfs");
        });

        JMenuItem dijButton = new JMenuItem("DIJ");
        dijButton.addActionListener(e -> {
            talk("dij");
        });

        JMenuItem aStarButton = new JMenuItem("A*");
        aStarButton.addActionListener(e -> {
            talk("as");
        });

        JMenuItem kruskalButton = new JMenuItem("Kruskal");
        kruskalButton.addActionListener(e -> {
            talk("kruskal");
        });

        JMenuItem primButton = new JMenuItem("Prim");
        primButton.addActionListener(e -> {
            talk("prim");
        });

        JMenu algorithmMenu = new JMenu("algorithms");
        algorithmMenu.add(bfsButton);
        algorithmMenu.add(dijButton);
        algorithmMenu.add(aStarButton);
        algorithmMenu.add(new JSeparator());
        algorithmMenu.add(kruskalButton);
        algorithmMenu.add(primButton);
        _ui = new FileChooserUI(items,algorithmMenu);
    }

    public String getSelectedFile(){
        System.out.println(selectedFile);
        return selectedFile;
    }

    private void updateSelected(){
        selectedFile = _ui.getCombo().getSelectedItem().toString();
    }

    public JMenuBar getUI() { return _ui.getMainPanel(); }

}
