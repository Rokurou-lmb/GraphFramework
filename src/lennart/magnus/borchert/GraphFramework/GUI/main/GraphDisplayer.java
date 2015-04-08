package lennart.magnus.borchert.GraphFramework.GUI.main;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import javax.swing.*;

/**
 * Created by Gery on 08.04.2015.
 */
public class GraphDisplayer {

    private GraphDisplayerUI _ui;

    private JGraphModelAdapter adapter;

    public GraphDisplayer(){
        _ui = new GraphDisplayerUI();
    }

    public void paintGraph(Graph graph){
        adapter = new JGraphModelAdapter(graph);
        _ui.setGraph(adapter);
    }

    public JPanel getUI(){ return _ui.getMainPanel(); }

}
