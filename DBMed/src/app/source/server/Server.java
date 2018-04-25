package server;

import config.Config;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    private static List<ClientConnection> connections = new ArrayList<>();

    private Thread serverThread;

    private final int port = Config.PORT;

    private ServerSocket serverSocket;

    Server() {

        serverThread = new Thread(this, "Server thread");
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverThread.start();
    }


    @Override
    public void run() {

        try {
            while (true) {
                ClientConnection clientConnection = new ClientConnection(serverSocket.accept());
                connections.add(clientConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
