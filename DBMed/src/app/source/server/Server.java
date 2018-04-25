package server;

import config.Config;
import dbConnector.DBConnector;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {

    private static final String databaseLogin = "root";
    private static final String databasePassword = "admin";

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

        DBConnector.getInstance().setLogin(databaseLogin);
        DBConnector.getInstance().setPassword(databasePassword);
        DBConnector.getInstance().startConnection();

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

    public static boolean checkRoot(String login, String password){
        if (databaseLogin.equals(login) && databasePassword.equals(password))
            return true;
        else return false;
    }


}
