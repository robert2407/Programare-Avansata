package org.example;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton exitBtn, loadBtn, saveBtn;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        loadBtn = new JButton("Load");
        saveBtn = new JButton("Save");
        exitBtn = new JButton("Exit");

        loadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = "C:\\Users\\Robert\\IdeaProjects\\C6\\src\\main\\java\\org\\example\\input.txt";
                File file = new File(filePath);
                try {
                    Scanner scanner = new Scanner(file);

                    int first = scanner.nextInt();
                    float second = scanner.nextFloat();
                    System.out.println("1: " + first);
                    System.out.println("2: " + second);
                    DrawingPanel drawingPanel = frame.canvas;
                    drawingPanel.paintComponent(first, second);
                    scanner.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("Nu este fisierul: " + ex.getMessage());
                }
            }
        });

        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Robot robot = new Robot();
                    int canvasWidth = 700;
                    int canvasHeight = 575;
                    BufferedImage canvasImage = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = canvasImage.createGraphics();
                    Rectangle canva = new Rectangle(50, 85, canvasWidth, canvasHeight);
                    BufferedImage screenshotImage = robot.createScreenCapture(canva);
                    graphics.drawImage(screenshotImage, 0, 0, null);
                    File outputFile = new File("image.png");
                    ImageIO.write(canvasImage, "png", outputFile);
                    System.out.println("Imaginea salvata la: " + outputFile.getAbsolutePath());
                } catch (AWTException | IOException ex) {
                    ex.printStackTrace();
                }
                ////////// sau asa //////////
                //  frame.canvas.saveImage("graf.png");
            }
        });




        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        setLayout(new GridLayout(1, 3));
        add(loadBtn);
        add(saveBtn);
        add(exitBtn);
    }
}