package com.company;

import javax.swing.*;

public class ControlPanel extends JPanel {
    JButton saveBtn;
    JButton loadBtn;
    JButton resetBtn;
    JButton exitBtn;

    public ControlPanel() {
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
    }
}
