package client_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Running at 9999");
        ServerSocket listener = new ServerSocket(9999);
        int countClient = 0;
        while (true) {
            Socket clientSocket = listener.accept();
            ConnectedThread newClient = new ConnectedThread(clientSocket, countClient);
            newClient.start();
        }
    }
}

class ConnectedThread extends Thread {

    Socket socket;
    int id;
    BufferedReader in;
    PrintWriter out;

    ConnectedThread(Socket clientSocket, int numClients) throws IOException {
        this.socket = clientSocket;
        this.id = numClients;
        System.out.println("Client " + socket + " connected! Client number " + id);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Connected to server!");
            while (true) {
                String buf = in.readLine();
                System.out.println("RECV: " + buf);
                if (buf == null || buf.isEmpty()) {
                    break;
                }
                String[] strArr = buf.split(" ");
                Integer[] intArr = new Integer[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    intArr[i] = Integer.parseInt(strArr[i]);
                }
                Integer[] intAns = new Sorter().sort(intArr);
                String[] strAns = Arrays.stream(intAns).map((t) -> {
                    return String.valueOf(t); //To change body of generated lambdas, choose Tools | Templates.
                }).toArray(String[]::new);
                out.println("ANS: " + Arrays.toString(strAns));
            }
        } catch (Exception e) {
            System.out.println("Error: client " + id + "; message " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ConnectedThread.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                System.out.println("Client " + id + " closed!");
            }
        }

    }

}
