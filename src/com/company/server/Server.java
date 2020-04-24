package com.company.server;

import com.company.Main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        DataInputStream din;
        DataOutputStream dout;
        ExpressionSolver exp = new ExpressionSolver();
        String expression;
        String res;

        try {
            serverSocket = new ServerSocket(Main.PORT);
            socket = serverSocket.accept();
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            System.out.println("SERVER: Server is waiting for a mathematical expression.");
            while(true) {
                expression = din.readUTF();
                if (expression.equals("EXIT")) break;
                System.out.println("SERVER: Got from client " + expression + "\nSolving it...");
                exp.setExpression(expression);
                res = exp.solveExpression();
                System.out.println(exp);
                dout.writeUTF(res);
                dout.flush();
            }
            System.out.println("Server has finished its work.");
            din.close();
            dout.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
