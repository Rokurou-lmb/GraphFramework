package lennart.magnus.borchert.GraphFramework.GUI.main;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gery on 08.04.2015.
 */
public class GraphDisplayerUI {

    private JPanel _main;
    private JGraph _graph;

    public GraphDisplayerUI(){
        _graph = new JGraph();
        _main = new JPanel();
        _main.setPreferredSize(new Dimension(1024, 786));
        _graph.setPreferredSize(new Dimension(1024, 786));
        _main.add(_graph);
    }

    public void setGraph(JGraphModelAdapter adapter){
        _main.remove(_graph);
        _graph = new JGraph(adapter);
        _graph.setPreferredSize(new Dimension(1024, 786));
        _main.add(_graph);
        _main.updateUI();
    }

    public JPanel getMainPanel(){
        return _main;
    }
}
