package lennart.magnus.borchert.GraphFramework.GUI.main;

import javax.swing.*;
import java.awt.*;

public class MainFrameUI {

    private JFrame _frame;

    public MainFrameUI(JPanel chooser,JPanel displayer){
        _frame = new JFrame("GKA");
        _frame.getContentPane().add(chooser, BorderLayout.NORTH);
        _frame.getContentPane().add(displayer, BorderLayout.CENTER);
        _frame.pack();
        _frame.setVisible(true);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}