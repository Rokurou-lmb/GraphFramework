package lennart.magnus.borchert.GraphFramework.GUI.main;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.graph.JGraphSimpleLayout;
import com.jgraph.layout.organic.JGraphFastOrganicLayout;
import com.jgraph.layout.organic.JGraphOrganicLayout;
import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

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

    public void setGraph(JGraphModelAdapter adapter){
        if(_graph!=null)_main.remove(_graph);
        _graph = new JGraph(adapter);
        _graph.setPreferredSize(new Dimension(1024, 786));

        JGraphFacade jgf = new JGraphFacade(_graph);
        JGraphLayout layout = new JGraphSimpleLayout(JGraphSimpleLayout.TYPE_CIRCLE);
        layout.run(jgf);
//        JGraphOrganicLayout layoutifier = new JGraphOrganicLayout();
//        layoutifier.setRadiusScaleFactor(1);
//        layoutifier.run(jgf);

        final Map nestedMap = jgf.createNestedMap(true, true);
        _graph.getGraphLayoutCache().edit(nestedMap);

        _graph.getGraphLayoutCache().update();
        _graph.refresh();
        _main.add(_graph);
        _main.updateUI();
    }

    public JPanel getMainPanel(){
        return _main;
    }
}
