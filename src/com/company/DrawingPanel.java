package com.company;

import sun.security.krb5.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

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
        resetPaint(false);
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
        if(configurationPanel.getPressedButton() == 1) {
            graphics.fillOval(x, y, 20, 15);
            return ;
        }

        if(sides <= 2) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 sides");
            return ;
        }

        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        int radius = random.nextInt(15) + 20; //generate a random number
        Color color =  new Color(r, g, b); //create a transparent random Color.
        graphics.setColor(color);
        graphics.fill(new RegularPolygon(x, y, radius, sides));
    }
    @Override
    public void update(Graphics g) { } //Why did I do that?

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void resetPaint(boolean showDialog) {
        graphics.setColor(Color.WHITE); //fill the image with white
        graphics.fillRect(0, 0, W, H);

        if(showDialog)
            JOptionPane.showMessageDialog(frame, "Paint zone cleared");

        repaint();
    }

    public void savePaint() throws IOException {
        final JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(frame);

        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Opening: " + file.getName());
            ImageIO.write(image, "jpg", file);
        }
        else {
            System.out.println("Cancelled");
        }
    }

    public void loadPaint() throws IOException {
        final JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(frame);

        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Opening: " + file.getName());
            image = ImageIO.read(file);
            graphics = image.createGraphics();

            repaint();
        }
        else {
            System.out.println("Cancelled");
        }
    }
}
