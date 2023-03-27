package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.util.List;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    private Image image;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        initPanel();
    }

    private void initPanel() {
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
    }


    @Override
    protected void paintComponent(Graphics graphics) {
//        graphics.drawImage(image, 0, 0, this);
    }
}
