import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calc implements ActionListener {
    JFrame f;
    JTextField t;
    JPanel p;
    RoundButton b1,b2,b3,b4,b5,b6,b7,b8,b9,b0,bdec,beq,bdel,bclr,brem;
    OpButton bdiv, bmul, bsub, badd;

    static String a = "0", b = "0";
    static char operator = ' ';

    Calc() {
        //----------------------1ROW-----------------------------------
        t = new JTextField("0");
        t.setBounds(0,20,260,80);
        t.setBackground(Color.black);
        t.setForeground(Color.white);
        t.setHorizontalAlignment(JLabel.RIGHT);
        t.setBorder(null);
        t.setFont(new Font("Helvetica", 0, 47));
        t.setCaretColor(Color.BLACK);
        t.setEditable(false);

        //----------------------2ROW-----------------------------------
        bclr = new RoundButton("AC");
        bclr.setBounds(5,105,60,60);
        bclr.setBackground(Main.LIGTH_GRAY);
        bclr.setForeground(Color.BLACK);

        bdel=new RoundButton("C");
        bdel.setBounds(70,105,60,60);
        bdel.setBackground(Main.LIGTH_GRAY);
        bdel.setForeground(Color.BLACK);

        brem=new RoundButton("%");
        brem.setBounds(135,105,60,60);
        brem.setBackground(Main.LIGTH_GRAY);
        brem.setForeground(Color.BLACK);

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
        b0.setBounds(5,365,125,60);
        b0.setFont(new Font("Helvetica", 0, 27));

        bdec=new RoundButton(".");
        bdec.setBounds(135,365,60,60);
        bdec.setFont(new Font("Helvetica", 0, 27));

        beq = new RoundButton("=");
        beq.setBounds(200,365,60,60);
        beq.setBackground(Main.ORANGE);
        beq.setFont(new Font("Helvetica", 0, 40));

        //-------------------------JPANEL-------------------------------
        p = new JPanel();
        p.add(t);
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
        p.add(bdec);
        p.add(b0);
        p.add(beq);
        p.add(badd);
        p.add(bdel);
        p.add(bclr);
        p.add(brem);
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
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();

        // numbers or the dot
        if(source == b0 || source == b1 || source == b2 || source == b3 || source == b4 || source == b5 || source == b6 || source == b7 || source == b8 || source == b9 || source == bdec)
            addToActiveVar(source.getText());

        // operations
        if (source == badd) {
            badd.choose();
        } else if (source == bsub) {
            bsub.choose();
        } else if(source == bmul) {
            bmul.choose();
        } else if(source == bdiv) {
            bdiv.choose();
        } else if(source == beq) {
//            switch(operator) {
//                case '+': result=a+b;
//                    break;
//                case '-': result=a-b;
//                    break;
//                case '*': result=a*b;
//                    break;
//                case '/': result=a/b;
//                    break;
//                default: result = 0;
//            }
//            t.setText(""+result);
        }

        if(source == bclr) {
            if (operator == ' ') {

            } else {

            }

            t.setText(a);
        } else if(source == bdel) {

        }

        if (t.getText().length() > 1 && t.getText().charAt(0) == '0')
            t.setText(t.getText().substring(1));
    }

    private void addToActiveVar(String s) {
        if (operator == ' ') {
            a = a.concat(s);
        } else {
            b = b.concat(s);
        }
    }
}