package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ControlPanel extends JPanel {
    JButton saveBtn;
    JButton loadBtn;
    JButton resetBtn;
    JButton exitBtn;
    DrawingPanel drawingPanel;

    public ControlPanel(DrawingPanel panel) {
        drawingPanel = panel;
        saveBtn = new JButton("Save");
        loadBtn = new JButton("Load");
        resetBtn = new JButton("Reset");
        exitBtn = new JButton("Exit");

        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);

        setVisible(true);

        exitBtn.addActionListener(e -> System.exit(0));

        resetBtn.addActionListener(e -> drawingPanel.resetPaint(true));

        saveBtn.addActionListener(e -> {
            try {
                drawingPanel.savePaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        loadBtn.addActionListener(e -> {
            try {
                drawingPanel.loadPaint();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
}
