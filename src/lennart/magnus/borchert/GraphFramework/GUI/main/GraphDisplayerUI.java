package lennart.magnus.borchert.GraphFramework.GUI.main;

import org.jgraph.JGraph;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Gery on 08.04.2015.
 */
public class GraphDisplayerUI {

    private JPanel _main;
    private JGraph _graph;

    public GraphDisplayerUI(){
//        _graph = new JGraph();
        _main = new JPanel();
        _main.setPreferredSize(new Dimension(1024, 786));
//        _graph.setPreferredSize(new Dimension(1024, 786));
//        _main.add(_graph);
    }

    

    public JPanel getMainPanel(){
        return _main;
    }
}
