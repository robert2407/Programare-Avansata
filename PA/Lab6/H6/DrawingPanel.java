package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;


public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final static int W = 800, H = 600;
    final Color play1Color = Color.RED;
    final Color play2Color = Color.BLUE;
    List<Point> verticesGlobal;
    int colorNum = 0;
    int[][] matrix;
    int currentPlayer = 1;
    int numberOfDots;
    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        initPanel();
    }

    private void initPanel() {
        class Edge {
            Point p1, p2;
            public Edge(Point p1, Point p2) {
                this.p1 = p1;
                this.p2 = p2;
            }
        }
        setPreferredSize(new Dimension(W, H));
        setBorder(BorderFactory.createEtchedBorder());
        Map<Edge, Color> edgeColors = new HashMap<>();

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < verticesGlobal.size() - 1; i++) {
                    Point p1 = verticesGlobal.get(i);
                    for (int j = i + 1; j < verticesGlobal.size(); j++) {
                        Point p2 = verticesGlobal.get(j);
                        Edge edge = new Edge(p1, p2);
                        if (isOnLine(e.getPoint(), p1, p2) && !edgeColors.containsKey(edge)) {
                            Graphics g = getGraphics();
                            Color color = currentPlayer == 1 ? play1Color : play2Color;
                            colorNum = colorNum + 1;
                            g.setColor(color);
                            g.drawLine(p1.x, p1.y, p2.x, p2.y);
                            edgeColors.put(edge, color);

                            if (gameStatus(color)) {
                                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " won!");
                                return;
                            }

                            currentPlayer = currentPlayer == 1 ? 2 : 1;

                            if (colorNum == numberOfDots) {
                                JOptionPane.showMessageDialog(frame, "Game Over! Draw!");
                                return;
                            }
                        }
                    }
                }
            }
            private boolean gameStatus(Color color) {
                for (int i = 0; i < verticesGlobal.size(); i++) {
                    Point p1 = verticesGlobal.get(i);
                    for (int j = i + 1; j < verticesGlobal.size(); j++) {
                        Point p2 = verticesGlobal.get(j);
                        Edge edge1 = new Edge(p1, p2);
                        if (edgeColors.get(edge1) == color) {
                            for (int k = j + 1; k < verticesGlobal.size(); k++) {
                                Point p3 = verticesGlobal.get(k);
                                Edge edge2 = new Edge(p2, p3);
                                Edge edge3 = new Edge(p3, p1);
                                if (edgeColors.get(edge2) == color && edgeColors.get(edge3) == color) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                return false;
            }

            private boolean isOnLine(Point p, Point p1, Point p2) {
                double distance = Math.abs((p2.y - p1.y) * p.x - (p2.x - p1.x) * p.y + p2.x * p1.y - p2.y * p1.x) / Math.sqrt(Math.pow(p2.y - p1.y, 2) + Math.pow(p2.x - p1.x, 2));
                return distance <= 0.5;
            }
        });
    }

    protected void paintComponent(int numVertices, double edgeProbability) {
        Graphics g = getGraphics();
        g.clearRect(0, 0, 800,600);
        List<Point> vertices = new LinkedList<>();
        double boardRadius = H / 2 - 10;
        double diff = 2 * Math.PI / numVertices;
        int centerX = W / 2;
        int centerY = H / 2;

        for (int i = 0; i < numVertices; i++) {
            double angle = i * diff;
            int x = (int) Math.round(centerX + boardRadius * Math.cos(angle));
            int y = (int) Math.round(centerY + boardRadius * Math.sin(angle));
            vertices.add(new Point(x, y));
        }

        verticesGlobal = vertices;
//        System.out.println(verticesGlobal);

        g.setColor(Color.GRAY);
        int c = 0;

        int n = vertices.size();
        int[][] array = new int[n][n];

        for (int i = 0; i < numVertices - 1; i++) {
            for (int j = i + 1; j < numVertices; j++) {
                if (Math.random() < edgeProbability) {
                    Point p1 = vertices.get(i);
                    Point p2 = vertices.get(j);
                    c = c + 1;  //contor pentru nr de linii desenates
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    array[i][j] = 1;
                    array[j][i] = 1;
                }
            }
        }

        matrix = array;

        System.out.println("Matricea: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        this.numberOfDots = c;

        g.setColor(Color.BLACK);
        int nodeSize = 15;
        for (Point vertex : vertices) {
            g.fillOval(vertex.x - nodeSize / 2, vertex.y - nodeSize / 2, nodeSize, nodeSize);
        }
    }

    public void saveImage(String filename) {
        try {
            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            paint(image.getGraphics());
            File output = new File("board.png");
            ImageIO.write(image, "png", output);
        } catch (IOException ex) {
            System.out.println("Error at saving.");
        }
    }
}