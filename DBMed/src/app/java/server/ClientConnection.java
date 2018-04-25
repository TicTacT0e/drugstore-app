package server;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection implements Runnable {

    private Thread clientConnectionThread;

    private Socket socket;

    public ClientConnection(Socket socket) throws IOException{
        clientConnectionThread = new Thread(this, "Client Connection Thread");

        this.socket = socket;


        clientConnectionThread.start();
    }

    @Override
    public void run() {

    }
}
