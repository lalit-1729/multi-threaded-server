package org.example.multithreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class MultiThreadedServer {
    private final ExecutorService threadPool;

    public MultiThreadedServer(int poolSize) {
        threadPool = Executors.newFixedThreadPool(poolSize);
    }


    public Consumer<Socket> getConsumer(){
        return new Consumer<Socket>() {

            @Override
            public void accept(Socket socket) {
                try {
                    PrintWriter toClient = new PrintWriter(socket.getOutputStream());
                    Thread.sleep(20);
                    toClient.println("Hello from the server");
                    toClient.close();
                    socket.close();
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args){
        int poolSize = 20;
        MultiThreadedServer server = new MultiThreadedServer(poolSize);
        try {
            server.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void run() throws IOException {
        int port = 8010;
        ServerSocket serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(60_000);

        while (true){
            System.out.println("Waiting for connection at port: " + port + "...");
            Socket socket = serverSocket.accept();
            System.out.println("Accepted connection from " + socket.getRemoteSocketAddress());

            Thread  thread = new Thread(() -> getConsumer().accept(socket));
            threadPool.execute(thread);
        }
    }
}
