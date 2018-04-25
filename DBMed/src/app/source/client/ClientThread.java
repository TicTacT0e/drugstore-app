package client;

import config.Config;
import handle.EventNamespace;
import handle.HandleData;
import loggs.Logs;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread implements Runnable{

    private Thread clientThread;
    private Socket socket;
    private InetAddress inetAddress;
    private int port = Config.PORT;
    private ObjectInputStream obInput;
    private ObjectOutputStream obOutput;

    private HandleData handleData = new HandleData();

    public ClientThread(){
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

    private void send(HandleData handleData){
        try {
            obOutput.reset();
            obOutput.writeObject(handleData);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
