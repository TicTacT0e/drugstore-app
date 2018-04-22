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
            ResultSet resultSetMedProd = statement.executeQuery("SELECT * FROM DB_Receipt_of_Medicines.MedProd");

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

        String updateQuery = "UPDATE DB_Receipt_of_Medicines.MedProd SET nameMed = '" + medProd.getNameMed() + "', indications = '" + medProd.getIndications() +
                "', unit = '" + medProd.getUnit() + "', quantityInPac = '" + String.valueOf(medProd.getQuantityInPac()) + "', " +
                "manufactName = '" + medProd.getManufactName() + "' WHERE medCode = '" + String.valueOf(medProd.getMedCode()) + "';";

        update(updateQuery);
    }

    @Override
    public void delete(MedProd medProd) {
        String deleteQuery = "DELETE FROM DB_Receipt_of_Medicines.MedProd WHERE medCode = '" + String.valueOf(medProd.getMedCode()) + "';";

        delete(deleteQuery);
    }

    @Override
    public void insert() {
        String insertQuery = "INSERT INTO DB_Receipt_of_Medicines.MedProd (medCode, nameMed, indications, unit, quantityInPac, manufactName) " +
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
