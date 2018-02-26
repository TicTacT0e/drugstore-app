package collectionsData;

import address.ConnectWindowController;
import collectionsData.dataInterfaces.MedProdDataTable;
import com.mysql.jdbc.Connection;
import company.DBConnector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MedProdCollectionData implements MedProdDataTable{

    private ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();

    private DBConnector dbConnector;



    //private ConnectWindowController connectWindowController;

    public MedProdCollectionData() {
    }
/*
    public MedProdCollectionData(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
*/
    public void readData() {

        java.sql.Connection connection = dbConnector.getConnection();

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

    public void setDbConnector(DBConnector dbConnector) {
        this.dbConnector = dbConnector;
    }
}
