package com.company.gui;


import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

class RoundButton extends JButton {
    Shape shape;

    public RoundButton(String label) {
        super(label);
        setContentAreaFilled(false);
        setForeground(Color.WHITE);
        setBackground(Main.DARK_GRAY);
        setBorder(null);
        setMargin(new Insets(0, 0, 0, 0));
        setFont(new Font("Helvetica", 0, 27));
        setFocusPainted(false);
        addActionListener(Main.calculator);
    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        if (getModel().isArmed()) {
            g2.setColor(new Color(51,51,51));
        } else {
            g2.setColor(getBackground());
        }

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillOval(0, 0, getSize().width, getSize().height);

        super.paintComponent(g);
    }

    // only clicks within the round shape should be accepted
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }

        return shape.contains(x, y);
    }
}