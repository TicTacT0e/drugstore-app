package collectionsData;

import collectionsData.dataInterfaces.DataInterface;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CollectionData implements DataInterface{

    protected static Connection connection;

    public static boolean deleteRow;

    public CollectionData() {
    }

    public static void setConnection(Connection connection) {
        CollectionData.connection = connection;
    }

    public void readData() {}

    public void update(String updateQuery){
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String deleteQuery){
        deleteRow = false;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteQuery);
            deleteRow = true;
        } catch (SQLException e) {
            deleteRow = false;
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error!");
            alert.setHeaderText("Cannot delete.");
            alert.setContentText("Cannot delete a parent row.");
            alert.showAndWait();
        }
    }

    public void insert(String insertQuery){
        try (Statement statement = connection.createStatement()) {
            statement.execute(insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
