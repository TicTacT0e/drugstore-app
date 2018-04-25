package server;

import handle.EventNamespace;
import handle.HandleData;
import loggs.Logs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;



public class ClientConnection implements Runnable {

    private boolean stopThreadFlag = false;

    private ObjectOutputStream obOutput;
    private ObjectInputStream obInput;
    private Thread clientConnectionThread;
    private Socket socket;

    EventNamespace event = null;
    private HandleData handleData;
    private String clientUsername;

    public ClientConnection(Socket socket) throws IOException{
        clientConnectionThread = new Thread(this, "Client Connection Thread");

        this.socket = socket;

        obOutput = new ObjectOutputStream(this.socket.getOutputStream());
        obInput = new ObjectInputStream(this.socket.getInputStream());

        clientConnectionThread.start();
    }

    @Override
    public void run() {
        try {
            while (!stopThreadFlag) {
                handleData = (HandleData) obInput.readObject();

                event = handleData.getEvent();

                switch (event) {
                    case REGISTRATION:
                        clientUsername = handleData.getUsername();
                        Logs.logIn(handleData.getUsername() + " registered.");
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void closeConnection(){
        try {
            setStopThreadFlag();
            obOutput.close();
            obInput.close();
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void setStopThreadFlag(){
        stopThreadFlag = true;
    }
}
