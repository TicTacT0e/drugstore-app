package collectionsData;

import collectionsData.dataInterfaces.MedProdDataTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class MedProdCollectionData extends CollectionData implements MedProdDataTable {

    private ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();

    public static boolean deleteRow;


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

    public void delete(MedProd medProd) {

        deleteRow = false;
        String deleteQuery = "DELETE FROM db_receipt_of_medicines.medprod WHERE medCode = '" + String.valueOf(medProd.getMedCode()) + "';";

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

    public void insert() {

        try (Statement statement = connection.createStatement()) {
            String inserQuery = "INSERT INTO db_receipt_of_medicines.medprod (medCode, nameMed, indications, unit, quanityInPac, manufactName) " +
                    "VALUES (" + String.valueOf(medProdsData.get(medProdsData.size() - 1).getMedCode()) + ", '" + medProdsData.get(medProdsData.size() - 1).getNameMed() +
                    "', '" + medProdsData.get(medProdsData.size() - 1).getIndications() + "', '" + medProdsData.get(medProdsData.size() - 1).getUnit() +
                    "', " + String.valueOf(medProdsData.get(medProdsData.size() - 1).getQuantityInPac()) + ", " +
                    "'" + medProdsData.get(medProdsData.size() - 1).getManufactName() + "');";

            statement.execute(inserQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(MedProd medProd) {

        try (Statement statement = connection.createStatement()) {
            String updateQuery = "UPDATE db_receipt_of_medicines.medprod SET nameMed = '" + medProd.getNameMed() + "', indications = '" + medProd.getIndications() +
                    "', unit = '" + medProd.getUnit() + "', quanityInPac = '" + String.valueOf(medProd.getQuantityInPac()) + "', " +
                    "manufactName = '" + medProd.getManufactName() + "' WHERE medCode = '" + String.valueOf(medProd.getMedCode()) + "';";

            statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ObservableList<MedProd> getMedProdData() {
        return medProdsData;
    }

}
