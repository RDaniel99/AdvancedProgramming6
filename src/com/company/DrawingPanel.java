package com.company;

import sun.security.krb5.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class DrawingPanel extends JPanel {
    final MainFrame frame;
    final ConfigurationPanel configurationPanel;
    final static int W = 1300, H = 1000;
    BufferedImage image; //the offscreen image
    Graphics2D graphics; //the "tools" needed to draw in the image

    public DrawingPanel(MainFrame frame, ConfigurationPanel panel) {
        this.frame = frame; createOffscreenImage(); init();
        configurationPanel = panel;
    }
    private void createOffscreenImage() {
        image = new BufferedImage(W, H, BufferedImage.TYPE_INT_ARGB);
        graphics = image.createGraphics();
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);
    }

    private void init() {
        setPreferredSize(new Dimension(W, H)); //don’t use setSize. Why?
        setBorder(BorderFactory.createEtchedBorder()); //for fun
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                drawShape(e.getX(), e.getY(), configurationPanel.getSides()); repaint();
            } //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }
    private void drawShape(int x, int y, int sides) {
        if(sides <= 2) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 sides");
            return ;
        }

        int radius = 20; //generate a random number
        Color color =  Color.red; //create a transparent random Color.
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
}
