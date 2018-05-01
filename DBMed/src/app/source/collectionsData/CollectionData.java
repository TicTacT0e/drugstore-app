package collectionsData;

import client.Client;
import collectionsData.dataInterfaces.DataInterface;
import handle.EventNamespace;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CollectionData implements DataInterface{

    protected static Connection connection;

    public static boolean deleteRow = false;

    public CollectionData() {
    }

    public static void setConnection(Connection connection) {
        CollectionData.connection = connection;
    }

    public void readData() {}

    public void update(String updateQuery){
        Client.getInstance().executeQuery(EventNamespace.EXECUTE_QUERY, updateQuery);
    }

    public void delete(String deleteQuery){

        deleteRow = Client.getInstance().executeDelete(EventNamespace.DELETE_ROW, deleteQuery);

        if (!deleteRow){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("Cannot delete.");
            alert.setContentText("Cannot delete a parent row.");
            alert.showAndWait();
        }
    }

    public void insert(String insertQuery){
        Client.getInstance().executeQuery(EventNamespace.EXECUTE_QUERY, insertQuery);
    }
}
