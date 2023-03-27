package org.example;

import org.example.MainFrame;

import javax.swing.*;
import java.awt.*;

class ConfigPanel extends JPanel {
    final MainFrame frame;
    private JLabel dotsLabel;
    private JLabel linesLabel;
    JSpinner dotsSpinner;
    JComboBox linesCombo;
    private JButton createButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        dotsLabel = new JLabel("Numarul de puncte: ");
        dotsSpinner = new JSpinner(new SpinnerNumberModel(6, 3, 10, 1));

        linesLabel = new JLabel("Porbabilitate: ");
        linesCombo = new JComboBox<>(new String[]{"1", "2", "3", "4"});
        createButton = new JButton("Joc nou");

        setLayout(new FlowLayout());
        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
    }
}
