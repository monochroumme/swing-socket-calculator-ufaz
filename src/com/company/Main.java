package com.company;

import com.company.gui.Calc;
import com.company.server.Server;

import java.awt.*;

public class Main {
    public static final Color ORANGE = new Color(254,159,20);
    public static final Color DARK_GRAY = new Color(51,51,51);
    public static final Color LIGHT_GRAY = new Color(165,165,165);
    public static Calc calculator;
    public static Server server;

    public static final int PORT = 2020;

    public static void main(String[] args) {
        server = new Server();
        server.start();
        calculator = new Calc();
	    calculator.init();
    }
}
