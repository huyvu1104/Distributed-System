/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 9999);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        Scanner scanner = new Scanner(System.in);
        System.out.println(in.readLine());
        while (true) {
            String buf = scanner.nextLine();
            if (buf.equals(" ")) {
                break;
            }
            out.println(buf);
            System.out.println(in.readLine());

        }
        clientSocket.close();
        scanner.close();
    }
}
