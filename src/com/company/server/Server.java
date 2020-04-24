package com.company.server;

import com.company.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public void run() {
        ServerSocket serverSocket; // ServerSocket of the server
        Socket socket; // Socket of the server
        BufferedReader in; // BufferedReader to read a mathematical expression
        PrintWriter pw; // PrintWriter to send the result to the user
        ExpressionSolver exp;

        try {
            serverSocket = new ServerSocket(Main.PORT); // Initialize ServerSocket
            System.out.println("Server is waiting for a mathematical expression.");
            socket = serverSocket.accept(); // Initialize socket to the given ServerSocket
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Initializing BufferedReader
            // to read the expression
            String expression = in.readLine(); // Reading the expression
            exp = new ExpressionSolver(expression);
            int res = exp.solveExpression();
            System.out.println(expression + " = " + res); // Printing an expression and the result

            pw = new PrintWriter(socket.getOutputStream()); // Initializing PrintWriter to send the result to the user
            pw.println(res); // Sending the result
            pw.flush(); // Flush!
            System.out.println("Server has finished its work.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

