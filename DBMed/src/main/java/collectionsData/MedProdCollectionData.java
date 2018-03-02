package collectionsData;

import collectionsData.dataInterfaces.MedProdDataTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MedProdCollectionData extends CollectionData implements MedProdDataTable {

    private ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();


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
    public void fillTestData() {
        medProdsData.add(new MedProd(1, "СhopChop", " ", " ", 0, " "));
        medProdsData.add(new MedProd(2, "SlapSlap", " ", " ", 0, " "));
    }

    public ObservableList<MedProd> getMedProdData() {
        return medProdsData;
    }

}
