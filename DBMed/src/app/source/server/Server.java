package server;

import config.Config;
import dbConnector.DBConnector;
import model.MedProd;
import model.Suppliers;
import model.Supply;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static boolean checkRoot(String login, String password) {
        if (databaseLogin.equals(login) && databasePassword.equals(password))
            return true;
        else return false;
    }

    public static ArrayList<MedProd> getMedProdTable(String query) {
        ResultSet tempResultSet;

        ArrayList<MedProd> tempMedProds = new ArrayList<>();
        try (Statement statement = DBConnector.getInstance().getConnection().createStatement()) {
            tempResultSet = statement.executeQuery(query);

            while (tempResultSet.next()) {
                tempMedProds.add(new MedProd(tempResultSet.getInt(1), tempResultSet.getString(2),
                        tempResultSet.getString(3), tempResultSet.getString(4),
                        tempResultSet.getInt(5), tempResultSet.getString(6)));
            }

            return tempMedProds;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Suppliers> getSuppliersTable(String query) {
        ResultSet tempResultSet;

        ArrayList<Suppliers> tempSuppliers = new ArrayList<>();
        try (Statement statement = DBConnector.getInstance().getConnection().createStatement()) {
            tempResultSet = statement.executeQuery(query);

            while (tempResultSet.next()) {
                tempSuppliers.add(new Suppliers(tempResultSet.getInt(1), tempResultSet.getString(2), tempResultSet.getString(3),
                        tempResultSet.getString(4), tempResultSet.getLong(5), tempResultSet.getString(6)));
            }

            return tempSuppliers;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Supply> getSuppliesTable(String query) {
        ResultSet tempResultSet;

        ArrayList<Supply> tempSupplies = new ArrayList<>();
        try (Statement statement = DBConnector.getInstance().getConnection().createStatement()) {
            tempResultSet = statement.executeQuery(query);

            while (tempResultSet.next()) {
                tempSupplies.add(new Supply(tempResultSet.getInt(1), tempResultSet.getInt(2), tempResultSet.getDate(3).toLocalDate(),
                        tempResultSet.getFloat(4), tempResultSet.getInt(5), tempResultSet.getInt(6)));
            }

            return tempSupplies;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
