package collectionsData;

import address.ConnectWindowController;
import collectionsData.dataInterfaces.MedProdDataTable;
import java.sql.Connection;
import company.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MedProdCollectionData implements MedProdDataTable{

    private ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();

    private static Connection connection;

    public MedProdCollectionData() {
    }

    public static void setConnection(Connection connection) {
        MedProdCollectionData.connection = connection;
    }

    public void readData() {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSetMedProd = statement.executeQuery("SELECT * FROM db_receipt_of_medicines.medprod");

            while (resultSetMedProd.next()) {
                medProdsData.add(new MedProd(resultSetMedProd.getInt(1), resultSetMedProd.getString(2),
                        resultSetMedProd.getString(3), resultSetMedProd.getString(4),
                        resultSetMedProd.getInt(5), resultSetMedProd.getString(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * EEE BOY
     */
    public void fillTestData(){
        medProdsData.add(new MedProd(1, "Pedor", " ", " ",0, " "));
        medProdsData.add(new MedProd(2, "Pidor", " ", " ",0, " "));
    }

    public ObservableList<MedProd> getData() {
        return medProdsData;
    }

}
