package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Server is started/n");

        ServerSocket ss = new ServerSocket(2020);
        System.out.println("Server is waiting for client's request/n");

        Socket socket = ss.accept();
        System.out.println("Request is accepted");

        System.out.println("Client connected");

        //requests
//        DataInputStream inp = new DataInputStream(socket.getInputStream());
//        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = buffer.readLine();

        System.out.println("Answer :" + answer);

//        while (true) {
//            String input = inp.readUTF();
//
//            if(input.equals("end"))
//                break;
//
//            System.out.println("The received equation is :-" + input);
//
//            int ans;
//
//            StringTokenizer st = new StringTokenizer(input);
//
//            int operand1 = Integer.parseInt(st.nextToken());
//            String operation = st.nextToken();
//            int operand2 = Integer.parseInt(st.nextToken());
//
//            if (operation.equals("+")) {
//                ans = operand1 + operand2;
//            }
//
//            else if (operation.equals("-")) {
//                ans = operand1 - operand2;
//            }
//            else if (operation.equals("*")) {
//                ans = operand1 * operand2;
//            }
//            else {
//                ans = operand1 / operand2;
//            }
//            System.out.println("Answer is :" );
//
//            // send the result back to the client.
//            out.writeUTF(Integer.toString(ans));
//        }
    }
}

