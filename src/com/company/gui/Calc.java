package com.company.gui;

import com.company.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Calc implements ActionListener {
    JFrame f;
    JTextField display;
    JPanel p;
    RoundButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,beq,bdel,bclr;
    OpButton bdiv, bmul, bsub, badd;

    static String expression = "0";

    private boolean connected = false;
    private Socket s;
    private DataInputStream  din;
    private DataOutputStream dout;

    public void init () {
        //----------------------1ROW-----------------------------------
        display = new JTextField("0");
        display.setBounds(0,20,260,80);
        display.setBackground(Color.black);
        display.setForeground(Color.white);
        display.setHorizontalAlignment(JLabel.RIGHT);
        display.setBorder(null);
        display.setFont(new Font("Helvetica", 0, 47));
        display.setCaretColor(Color.BLACK);
        display.setEditable(false);

        //----------------------2ROW-----------------------------------
        bclr = new RoundButton("AC");
        bclr.setBounds(5,105,60,60);
        bclr.setBackground(Main.LIGHT_GRAY);
        bclr.setForeground(Color.BLACK);

        bdel=new RoundButton("C");
        bdel.setBounds(70,105,125,60);
        bdel.setBackground(Main.LIGHT_GRAY);
        bdel.setForeground(Color.BLACK);

        bdiv=new OpButton("÷");
        bdiv.setBounds(200,105,60,60);
        bdiv.setFont(new Font("Helvetica", 0, 40));

        //----------------------3ROW-----------------------------------
        b7=new RoundButton("7");
        b7.setBounds(5,170,60,60);

        b8=new RoundButton("8");
        b8.setBounds(70,170,60,60);

        b9=new RoundButton("9");
        b9.setBounds(135,170,60,60);

        bmul=new OpButton("✖");
        bmul.setBounds(200,170,60,60);

        //----------------------4ROW-----------------------------------
        b4=new RoundButton("4");
        b4.setBounds(5,235,60,60);

        b5=new RoundButton("5");
        b5.setBounds(70,235,60,60);

        b6=new RoundButton("6");
        b6.setBounds(135,235,60,60);

        bsub=new OpButton("-");
        bsub.setBounds(200,235,60,60);
        bsub.setFont(new Font("Helvetica", 0, 60));

        //----------------------5ROW-----------------------------------
        b1=new RoundButton("1");
        b1.setBounds(5,300,60,60);

        b2=new RoundButton("2");
        b2.setBounds(70,300,60,60);

        b3=new RoundButton("3");
        b3.setBounds(135,300,60,60);

        badd=new OpButton("+");
        badd.setBounds(200,300,60,60);
        badd.setFont(new Font("Helvetica", 0, 40));

        //----------------------6ROW-----------------------------------
        b0=new RoundButton("0");
        b0.setBounds(5,365,190,60);
        b0.setFont(new Font("Helvetica", 0, 27));

        beq = new RoundButton("=");
        beq.setBounds(200,365,60,60);
        beq.setBackground(Main.ORANGE);
        beq.setFont(new Font("Helvetica", 0, 40));

        //-------------------------JPANEL-------------------------------
        p = new JPanel();
        p.add(display);
        p.add(b7);
        p.add(b8);
        p.add(b9);
        p.add(bdiv);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(bmul);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(bsub);
        p.add(b0);
        p.add(beq);
        p.add(badd);
        p.add(bdel);
        p.add(bclr);
        p.setBackground(Color.black);
        p.setLayout(null);

        //-----------------JFRAME-----------------------------------------
        f=new JFrame("iCalc");
        f.add(p);
        f.setVisible(true);
        f.setSize(280,470);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!s.isClosed()) {
                    try {
                        dout.writeUTF("EXIT");
                        dout.flush();
                        s.close();
                        dout.close();
                        din.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        connectToServer();
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        if (!connected) {
            if (source == bdel) {
                connectToServer();
            }
            return;
        }

        // Equals button
        if (source == beq) {
            askServerForAnswer();
        }

        // numbers or the dot
        if (source == b0 || source == b1 || source == b2 || source == b3 || source == b4 || source == b5 || source == b6 || source == b7 || source == b8 || source == b9)
            addToExpression(source.getText());

        // operations
        if (source == badd) {
            addToExpression(" + ");
        } else if (source == bsub) {
            addToExpression(" - ");
        } else if(source == bmul) {
            addToExpression(" * ");
        } else if(source == bdiv) {
            addToExpression(" / ");
        }

        // AC button
        if (source == bclr) {
            expression = "0";

        // C button
        } else if (source == bdel) {
            if (!(expression.length() == 1 && expression.charAt(0) == '0')) {
                if (expression.charAt(expression.length()-1) == ' ')
                    expression = expression.substring(0, expression.length() - 3);
                else expression = expression.substring(0, expression.length() - 1);
            }
            if (expression.length() == 0)
                expression = "0";
        }

        updateDisplay();
    }

    private void addToExpression(String s) {
        expression = removeFirstZero(expression.concat(s));
    }

    private void updateDisplay() {
        display.setText(expression);
    }

    private String removeFirstZero(String s) {
        if (s.length() > 1 && s.charAt(0) == '0')
            s = s.substring(1);
        return s;
    }

    private void askServerForAnswer() {
        System.out.printf("CLIENT: Server call to find %s\n", expression);

        // server call here
        try {
            dout.writeUTF(expression);
            dout.flush();
            expression = din.readUTF();
        } catch (Exception e) {
            problemConnecting();
            e.printStackTrace();
        }
        updateDisplay();
    }

    private void connectToServer() {
        try {
            s = new Socket(InetAddress.getLocalHost(), Main.PORT);
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            noProblemConnecting();
        } catch (Exception e) {
            problemConnecting();
            e.printStackTrace();
        }
        updateDisplay();
    }

    private void problemConnecting() {
        display.setFont(new Font("Helvetica", 0, 12));
        expression = "Couldn't connect to the server, please click R";
        connected = false;
        bdel.setText("R");
        bdel.setBackground(Color.RED);
        bdel.setForeground(Color.WHITE);
    }

    private void noProblemConnecting() {
        display.setFont(new Font("Helvetica", 0, 47));
        expression = "0";
        connected = true;
        bdel.setText("C");
        bdel.setBackground(Main.LIGHT_GRAY);
        bdel.setForeground(Color.BLACK);
    }
}
