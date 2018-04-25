package dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static DBConnector ourInstance = new DBConnector();
    public static DBConnector getInstance() {
        return ourInstance;
    }

    private String login;
    private String password;
    private static final String URL = "jdbc:mysql://localhost:3306/DB_Receipt_of_Medicines";

    private Connection connection = null;

    private DBConnector() {}

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void startConnection(){
        try {
            connection = DriverManager.getConnection(URL, login, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
