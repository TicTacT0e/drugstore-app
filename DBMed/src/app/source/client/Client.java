package client;

import config.Config;
import handle.EventNamespace;
import handle.HandleData;
import model.MedProd;
import model.Model;
import model.Suppliers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private static Client ourInstance = new Client();

    public static Client getInstance(){ return ourInstance; }

    private Socket socket;
    private InetAddress inetAddress;
    private int port = Config.PORT;
    private ObjectInputStream obInput;
    private ObjectOutputStream obOutput;

    private static HandleData handleData = new HandleData();

    private Client() {}

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

    public <T extends Model> ArrayList<T> executeQuery(EventNamespace event, String query, ArrayList<T> tableData) {
        handleData.setEvent(event);
        handleData.setQuery(query);
        send(handleData);

        try {
            handleData = (HandleData) obInput.readObject();

            if(handleData.getQuery().contains("MedProd")) {

                tableData = (ArrayList<T>) handleData.getMedProds();
            } else if (handleData.getQuery().contains("Suppliers")){
                tableData = (ArrayList<T>) handleData.getSuppliers();
            } else if (handleData.getQuery().contains("Supply")){
                tableData = (ArrayList<T>) handleData.getSupplies();
            }

            return tableData;

        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    /*
    public ArrayList<Suppliers> executeQuery(EventNamespace event, String query){
        handleData.setEvent(event);
        handleData.setQuery(query);
        send(handleData);

        try {
            handleData = (HandleData) obInput.readObject();

            return handleData.getSuppliers();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<MedProd> executeQuery(EventNamespace event, String query){
        handleData.setEvent(event);
        handleData.setQuery(query);
        send(handleData);

        try {
            handleData = (HandleData) obInput.readObject();

            return handleData.getMedProds();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
    */

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
