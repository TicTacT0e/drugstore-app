package collectionsData;

import collectionsData.dataInterfaces.SupplyDataInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplyCollectionData extends CollectionData implements SupplyDataInterface {

    private static ObservableList<Supply> supplyData = FXCollections.observableArrayList();

    @Override
    public void readData() {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSetSupply = statement.executeQuery("SELECT * FROM DB_Receipt_of_Medicines.Supply");

            while (resultSetSupply.next()) {
                supplyData.add(new Supply(resultSetSupply.getInt(1), resultSetSupply.getInt(2), resultSetSupply.getDate(3).toLocalDate(),
                        resultSetSupply.getFloat(4), resultSetSupply.getInt(5), resultSetSupply.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Supply supply) {

        String updateQuery = "UPDATE DB_Receipt_of_Medicines.Supply SET supplierCode = '" + String.valueOf(supply.getSupplierCode()) + "', admissionDate = '" + supply.getAdmissionDate().toString() +
                "', cost = '" + String.valueOf(supply.getCost()) + "', quantity = '" + String.valueOf(supply.getQuantity()) + "', " +
                "admissionCode = '" + String.valueOf(supply.getAdmissionCode()) + "' WHERE medCode = '" + String.valueOf(supply.getMedCode()) + "';";

        update(updateQuery);

    }

    @Override
    public void delete(Supply supply) {
        deleteRow = false;
        String deleteQuery = "DELETE FROM DB_Receipt_of_Medicines.Supply WHERE medCode = '" + String.valueOf(supply.getMedCode()) + "';";

        delete(deleteQuery);
    }

    @Override
    public void insert() {
        String insertQuery = "INSERT INTO DB_Receipt_of_Medicines.Supply (medCode, supplierCode, admissionDate, cost, quantity, admissionCode) " +
                "VALUES (" + String.valueOf(supplyData.get(supplyData.size() - 1).getMedCode()) + ", '" + String.valueOf(supplyData.get(supplyData.size() - 1).getSupplierCode()) +
                "', '" + supplyData.get(supplyData.size() - 1).getAdmissionDate().toString() + "', '" + String.valueOf(supplyData.get(supplyData.size() - 1).getCost()) +
                "', " + String.valueOf(supplyData.get(supplyData.size() - 1).getQuantity()) + ", " +
                "'" + String.valueOf(supplyData.get(supplyData.size() - 1).getAdmissionCode()) + "');";

        insert(insertQuery);

    }

    public ObservableList<Supply> getSupplyData() {
        return supplyData;
    }
}
