package lennart.magnus.borchert.graphFramework.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Gery on 08.04.2015.
 */
public class FileChooserUI {

    private JPanel _main;

    private JComboBox<String> _files;

    private JMenuBar _bar;
    private JMenuItem _load;

    public FileChooserUI(String[] files){
        this._main = new JPanel(new GridLayout());
        this._files = new JComboBox<String>(files);
        _main.add(_files);

    }

    public FileChooserUI(ArrayList<JMenuItem> items, JMenu algorithms){
        _main = new JPanel();
        _bar = new JMenuBar();
        _load = new JMenu("load");

        for(Object item : items){
            if(item instanceof JMenuItem){
                _load.add((JMenuItem)item);
            }
        }
        _bar.add(_load);
        _bar.add(algorithms);
        _main.add(_bar);
    }

    public JComboBox<String> getCombo(){
        return _files;
    }

    public JMenuBar getMainPanel(){
        return _bar;
    }
}
