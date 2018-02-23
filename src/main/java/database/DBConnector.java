package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private String username;
    private String password;
    private static final String URL = "jdbc:mysql://localhost:3306/db_receipt_of_medicines";

    private Connection connection = null;

    DBConnector (){}

    DBConnector (String username, String password){
        this.username = username;
        this.password = password;
    }

    public void startConnection(){
        try {
            connection = DriverManager.getConnection(URL, username, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public Connection getConnection() {
        return connection;
    }
}
