package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        linesCombo = new JComboBox<>(new String[]{"0.2", "0.4", "0.6", "0.8", "1"});
        createButton = new JButton("Joc nou");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int numNodes = (int) dotsSpinner.getValue();
                String strg = (String) linesCombo.getSelectedItem();
                Double probab = Double.parseDouble(strg);
                System.out.println(probab);
                DrawingPanel drawingPanel = frame.canvas;
                drawingPanel.paintComponent(numNodes, probab);
            }
        });

        setLayout(new FlowLayout());
        add(dotsLabel);
        add(dotsSpinner);
        add(linesLabel);
        add(linesCombo);
        add(createButton);
    }
}