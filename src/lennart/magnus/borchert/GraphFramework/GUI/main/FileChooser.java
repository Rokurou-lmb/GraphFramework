package lennart.magnus.borchert.GraphFramework.GUI.main;

import lennart.magnus.borchert.GraphFramework.GUI.main.listener.Talker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

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

        JMenu algorithmMenu = new JMenu("algorithms");
        algorithmMenu.add(bfsButton);
        algorithmMenu.add(dijButton);
        algorithmMenu.add(aStarButton);
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
