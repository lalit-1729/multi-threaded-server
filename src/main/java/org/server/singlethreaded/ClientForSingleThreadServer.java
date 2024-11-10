package org.server.singlethreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientForSingleThreadServer {

    public void run() throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        toServer.println("Hello From the client");

        String line = fromServer.readLine();
        System.out.println("response from the server is " + line);

        toServer.close();
        fromServer.close();
        socket.close();
    }

    public static void main(String[] args){
        ClientForSingleThreadServer server = new ClientForSingleThreadServer();
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
