package src;

import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;

public class Client{
    public static void main(String[] args) throws Exception {
            Socket s = new Socket("127.0.0.1", 2020);
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter an equation: ");
            System.out.println("Enter the operand");

            String str = scanner.nextLine();

            OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
            PrintWriter output = new PrintWriter(os);

            output.write(str);
            output.flush();
            s.close();
        }
    }

