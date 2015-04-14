package lennart.magnus.borchert.GraphFramework.GUI.main;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jgraph.JGraph;
import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;
import org.jgraph.graph.DefaultGraphCell;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphModelAdapter;

import com.jgraph.layout.JGraphFacade;
import com.jgraph.layout.JGraphLayout;
import com.jgraph.layout.graph.JGraphSimpleLayout;

import javax.swing.*;

import lennart.magnus.borchert.GraphFramework.Materialien.Vertex;

/**
 * Created by Gery on 08.04.2015.
 */
public class GraphDisplayer {

    private GraphDisplayerUI _ui;
    private JGraph _graph;

    private JGraphModelAdapter adapter;
    
    private List<Object> selected;

    public GraphDisplayer(){
    	selected = new ArrayList();
    	
        _ui = new GraphDisplayerUI();
       
    }
    
    private void setGraph(JGraphModelAdapter adapter){
        if(_graph!=null)_ui.getMainPanel().remove(_graph);
        _graph = new JGraph(adapter);
        _graph.addGraphSelectionListener(new GraphSelectionListener() {
			
			@Override
			public void valueChanged(GraphSelectionEvent e) {
				Object[] array = e.getCells();
				selected = Arrays.asList(array);
				System.out.println(selected.size());
			}
			
		});
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
        _ui.getMainPanel().add(_graph);
        _ui.getMainPanel().updateUI();
    }

    public void paintGraph(Graph graph){
        adapter = new JGraphModelAdapter(graph);
        setGraph(adapter);
    }
    
//    public List<Vertex> getSelected2(){
//    	return selected;
//    }

    public JPanel getUI(){ return _ui.getMainPanel(); }

}