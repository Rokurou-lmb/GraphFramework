package lennart.magnus.borchert.GraphFramework.GUI.main;

import javax.swing.*;

/**
 * Created by Gery on 08.04.2015.
 */
public class FileChooserUI {

    private JPanel _main;

    private JComboBox<String> _files;

    public FileChooserUI(String[] files){
        this._main = new JPanel();
        this._files = new JComboBox<String>(files);
        _main.add(_files);

    }

    public JComboBox<String> getCombo(){
        return _files;
    }

    public JPanel getMainPanel(){
        return _main;
    }
}
