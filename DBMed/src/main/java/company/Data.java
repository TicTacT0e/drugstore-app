package company;

import address.ConnectWindowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DBConnector;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Data {


    private DBConnector dbConnector;
    private ConnectWindowController connectWindowController;

    private ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();

    public Data(){
        dbConnector = connectWindowController.getDbConnector();

        try (Statement statement = dbConnector.getConnection().createStatement()) {
            ResultSet resultSetMedProd = statement.executeQuery("SELECT * FROM db_receipt_of_medicines.medprod");

            while (resultSetMedProd.next()){
                medProdsData.add(new MedProd(resultSetMedProd.getInt(1), resultSetMedProd.getString(2),
                        resultSetMedProd.getString(3), resultSetMedProd.getString(4),
                        resultSetMedProd.getInt(5), resultSetMedProd.getString(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<MedProd> getMedProdsData() {
        return medProdsData;
    }
}
