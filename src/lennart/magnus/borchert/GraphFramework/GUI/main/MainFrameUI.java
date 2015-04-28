package lennart.magnus.borchert.GraphFramework.GUI.main;

import javax.swing.*;

import java.awt.*;

public class MainFrameUI {

    private JFrame _frame;

    public MainFrameUI(JPanel chooser,JPanel displayer,JButton suchButton){
        _frame = new JFrame("GKA");
        _frame.setPreferredSize(new Dimension(1024,786));
        displayer.setPreferredSize(new Dimension(1024, 786));
        JPanel north = new JPanel(new GridLayout(1,2));
        north.add(chooser);
        north.add(suchButton);
        _frame.getContentPane().add(north, BorderLayout.NORTH);
        _frame.getContentPane().add(displayer, BorderLayout.CENTER);
        _frame.pack();
        _frame.setVisible(true);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JFrame getFrame(){return this._frame;}
}