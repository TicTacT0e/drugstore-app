package client;

import config.Config;
import controllers.DBConnectWindowController;
import handle.EventNamespace;
import handle.HandleData;
import loggs.Logs;
import server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread implements Runnable{

    private static ClientThread ourInstance = new ClientThread();

    public static ClientThread getInstance(){ return ourInstance; }

    private Thread clientThread;
    private Socket socket;
    private InetAddress inetAddress;
    private int port = Config.PORT;
    private ObjectInputStream obInput;
    private ObjectOutputStream obOutput;

    private static HandleData handleData = new HandleData();

    private ClientThread() {}

    public void startClientThread(){
        clientThread = new Thread(this, "Client thread");
        clientThread.start();
    }

    @Override
    public void run() {

    }

    public boolean connect(String addr){
        try {
            inetAddress = InetAddress.getByName(addr);
            socket = new Socket(inetAddress, port);

            obInput = new ObjectInputStream(socket.getInputStream());
            obOutput = new ObjectOutputStream(socket.getOutputStream());

            return true;
        }catch (IOException e){
            return false;
        }
    }

    public void clientRegistration(EventNamespace event, String username){
        handleData.setEvent(event);
        handleData.setUsername(username);
        send(handleData);
    }

    public boolean checkDBRoots(EventNamespace event, String login, String password){
        handleData.setEvent(event);
        handleData.setDatabaseLogin(login);
        handleData.setDatabasePassword(password);
        send(handleData);

        try {
            handleData = (HandleData) obInput.readObject();
            return handleData.getDatabaseRoot();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return false;
        }
    }

    private void send(HandleData handleData){
        try {
            obOutput.reset();
            obOutput.writeObject(handleData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
