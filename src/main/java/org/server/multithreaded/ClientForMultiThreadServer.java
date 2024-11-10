package org.server.multithreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.function.Consumer;

public class ClientForMultiThreadServer {

    public void run(Integer clientNumber) throws UnknownHostException, IOException {
        int port = 8010;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        PrintWriter toServer = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        toServer.println("Hello From the client");

        String line = fromServer.readLine();
        System.out.println("Response from the server for client "+ clientNumber + " is " + line);

        toServer.close();
        fromServer.close();
        socket.close();
    }

    public static void main(String[] args){
        ClientForMultiThreadServer client = new ClientForMultiThreadServer();
        for(int i = 0; i < 100; i++) {
            final Integer temp = i;
            Map.Entry<ClientForMultiThreadServer, Integer> entry = new Map.Entry<ClientForMultiThreadServer, Integer>() {
                @Override
                public ClientForMultiThreadServer getKey() {
                    return client;
                }

                @Override
                public Integer getValue() {
                    return temp;
                }

                @Override
                public Integer setValue(Integer value) {
                    return 0;
                }
            };
            Thread thread = new Thread(() -> client.getRunnable().accept(entry));
            thread.start();
        }
    }


    public Consumer<Map.Entry<ClientForMultiThreadServer, Integer>> getRunnable(){
        return (Map.Entry<ClientForMultiThreadServer, Integer> entry) -> {
                try {
                    entry.getKey().run(entry.getValue());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        };
    }
}
