package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationPanel extends JPanel {
    JLabel sidesLabel;
    JSpinner sidesSpinner;

    public ConfigurationPanel() {
        sidesLabel = new JLabel("Sides: ");
        sidesSpinner = new JSpinner();
        sidesSpinner.setValue(5);

        add(sidesLabel);
        add(sidesSpinner);

        setVisible(true);
    }

    public int getSides() {
        return (int)sidesSpinner.getValue();
    }
}
