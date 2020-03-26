package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigurationPanel extends JPanel {
    JLabel sidesLabel;
    JSpinner sidesSpinner;
    MainFrame frame;
    JButton eraseBtn;

    JLabel formLabel;
    JButton regPolyBtn;
    JButton ovalBtn;

    int pressedButton;
    boolean isEraserLastPressed;

    public boolean isEraserLastPressed() {
        return isEraserLastPressed;
    }

    public void setEraserLastPressed(boolean eraserLastPressed) {
        isEraserLastPressed = eraserLastPressed;
    }

    public ConfigurationPanel(MainFrame mainFrame) {
        this.frame = mainFrame;
        sidesLabel = new JLabel("Sides: ");
        sidesSpinner = new JSpinner();
        sidesSpinner.setValue(5);

        eraseBtn = new JButton("Eraser");
        isEraserLastPressed = false;

        pressedButton = 0;

        add(eraseBtn);
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

        eraseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isEraserLastPressed = true;
            }
        });

        regPolyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedButton = 0;
                isEraserLastPressed = false;

                regPolyBtn.setBackground(Color.orange);
                ovalBtn.setBackground(Color.gray);
            }
        });

        ovalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pressedButton = 1;

                isEraserLastPressed = false;

                System.out.println(pressedButton);
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
