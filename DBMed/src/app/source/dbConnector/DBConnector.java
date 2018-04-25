package dbConnector;

public class DBConnector {
    private static DBConnector ourInstance = new DBConnector();

    public static DBConnector getInstance() {
        return ourInstance;
    }

    private DBConnector() {
    }
}
