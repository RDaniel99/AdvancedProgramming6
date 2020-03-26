package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigurationPanel configPanel;
    DrawingPanel drawingPanel;
    ControlPanel controlPanel;

    public MainFrame() {
        configPanel = new ConfigurationPanel(this);
        drawingPanel = new DrawingPanel(this, configPanel);
        controlPanel = new ControlPanel(drawingPanel);

        setLayout(new BorderLayout());

        setTitle("Painter");
        setVisible(true);
        setSize(1300, 1000);

        add(configPanel, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }
}
