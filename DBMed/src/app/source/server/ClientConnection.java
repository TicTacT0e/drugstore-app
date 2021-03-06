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
    private static HandleData handleData = new HandleData();
    private String clientUsername;

    private boolean dbRoot = false;

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
                        Logs.getInstance().logIn(handleData.getUsername() + " registered");
                        break;

                    case ACCOUNT_CHECK:
                        dbRoot = Server.checkRoot(handleData.getDatabaseLogin(), handleData.getDatabasePassword());
                        if(dbRoot)
                            Logs.getInstance().logIn(handleData.getUsername() + " has access to the database");
                        else Logs.getInstance().logIn(handleData.getUsername() + "doesn't have access to the database");
                        handleData.setFlag(dbRoot);
                        send(handleData);
                        break;

                    case SELECT_QUERY:
                        if(handleData.getQuery().contains("MedProd")) {
                            handleData.setMedProds(Server.getTable(handleData.getQuery()));
                        }else if (handleData.getQuery().contains("Suppliers")){
                            handleData.setSuppliers(Server.getTable(handleData.getQuery()));
                        } else if (handleData.getQuery().contains("Supply")){
                            handleData.setSupplies(Server.getTable(handleData.getQuery()));
                        }

                        send(handleData);
                        break;

                    case EXECUTE_QUERY:
                        Server.executeQuery(handleData.getQuery());
                        break;

                    case DELETE_ROW:
                        handleData.setFlag(Server.executeDelete(handleData.getQuery()));
                        send(handleData);
                        break;

                    default:
                        break;
                }
            }
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void send(HandleData handleData){
        try {
            obOutput.reset();
            obOutput.writeObject(handleData);
        }catch (IOException e){
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
