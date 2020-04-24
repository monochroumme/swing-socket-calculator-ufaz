package com.company.gui;

import com.company.Main;

import java.awt.*;

public class OpButton extends RoundButton {
    public OpButton(String label) {
        super(label);
        unchoose();
    }

    public void choose() {
        setBackground(Color.WHITE);
        setForeground(Main.ORANGE);
    }

    public void unchoose() {
        setBackground(Main.ORANGE);
        setForeground(Color.WHITE);
    }
}
