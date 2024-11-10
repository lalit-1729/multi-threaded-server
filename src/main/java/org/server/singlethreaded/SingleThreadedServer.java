package org.server.singlethreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer {

    public static void main(String[] args){
        SingleThreadedServer server = new SingleThreadedServer();

        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void run() throws IOException, InterruptedException {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(90_000);

        while (true) {
            System.out.println("Waiting for connection at port: " + port +" .....");
            Socket acceptedSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + acceptedSocket.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptedSocket.getOutputStream(), true);
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
            Thread.sleep(20);
            toClient.println("Hello Server from the server");


            toClient.close();
            fromClient.close();
            acceptedSocket.close();
        }
    }
}
