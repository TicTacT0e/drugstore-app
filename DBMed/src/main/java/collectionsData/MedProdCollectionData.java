package collectionsData;

import collectionsData.dataInterfaces.MedProdDataInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MedProdCollectionData extends CollectionData implements MedProdDataInterface {

    private static ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();

    @Override
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

    @Override
    public void update(MedProd medProd) {

        String updateQuery = "UPDATE db_receipt_of_medicines.medprod SET nameMed = '" + medProd.getNameMed() + "', indications = '" + medProd.getIndications() +
                "', unit = '" + medProd.getUnit() + "', quanityInPac = '" + String.valueOf(medProd.getQuantityInPac()) + "', " +
                "manufactName = '" + medProd.getManufactName() + "' WHERE medCode = '" + String.valueOf(medProd.getMedCode()) + "';";

        update(updateQuery);
    }

    @Override
    public void delete(MedProd medProd) {
        String deleteQuery = "DELETE FROM db_receipt_of_medicines.medprod WHERE medCode = '" + String.valueOf(medProd.getMedCode()) + "';";

        delete(deleteQuery);
    }

    @Override
    public void insert() {
        String insertQuery = "INSERT INTO db_receipt_of_medicines.medprod (medCode, nameMed, indications, unit, quanityInPac, manufactName) " +
                "VALUES (" + String.valueOf(medProdsData.get(medProdsData.size() - 1).getMedCode()) + ", '" + medProdsData.get(medProdsData.size() - 1).getNameMed() +
                "', '" + medProdsData.get(medProdsData.size() - 1).getIndications() + "', '" + medProdsData.get(medProdsData.size() - 1).getUnit() +
                "', " + String.valueOf(medProdsData.get(medProdsData.size() - 1).getQuantityInPac()) + ", " +
                "'" + medProdsData.get(medProdsData.size() - 1).getManufactName() + "');";

        insert(insertQuery);
    }

    public ObservableList<MedProd> getMedProdData() {
        return medProdsData;
    }

}
