package lennart.magnus.borchert.GraphFramework.GUI.main;

import lennart.magnus.borchert.GraphFramework.GUI.main.listener.Talker;

import javax.swing.*;

/**
 * Created by Gery on 08.04.2015.
 */
public class FileChooser extends Talker{

    private FileChooserUI _ui;

    private String selectedFile = "none";

    public FileChooser(String[] files){
        _ui = new FileChooserUI(files);
        _ui.getCombo().addItemListener(e -> {
            updateSelected();
            talk();
        });
    }

    private void updateSelected(){
        selectedFile = _ui.getCombo().getSelectedItem().toString();
    }

    public JPanel getUI() { return _ui.getMainPanel(); }

}
