package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationPanel extends JPanel {
    JLabel sidesLabel;
    JSpinner sidesSpinner;
    MainFrame frame;

    JLabel formLabel;
    JButton regPolyBtn;
    JButton ovalBtn;

    int pressedButton;

    public ConfigurationPanel(MainFrame mainFrame) {
        this.frame = mainFrame;
        sidesLabel = new JLabel("Sides: ");
        sidesSpinner = new JSpinner();
        sidesSpinner.setValue(5);

        pressedButton = 0;

        add(sidesLabel);
        add(sidesSpinner);

        formLabel = new JLabel("Forms: (orange means selected) ");
        regPolyBtn = new JButton("Regular Polygon");
        ovalBtn = new JButton("Oval");

        regPolyBtn.setBackground(Color.orange);
        ovalBtn.setBackground(Color.gray);

        add(formLabel);
        add(regPolyBtn);
        add(ovalBtn);

        regPolyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedButton = 0;

                regPolyBtn.setBackground(Color.orange);
                ovalBtn.setBackground(Color.gray);
            }
        });

        ovalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedButton = 1;

                regPolyBtn.setBackground(Color.gray);
                ovalBtn.setBackground(Color.orange);
            }
        });

        setVisible(true);
    }

    public int getSides() {
        return (int)sidesSpinner.getValue();
    }

    public int getPressedButton() {
        return pressedButton;
    }
}
