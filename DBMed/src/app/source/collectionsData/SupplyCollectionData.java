package collectionsData;

import client.Client;
import collectionsData.dataInterfaces.SupplyDataInterface;
import handle.EventNamespace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SupplyCollectionData extends CollectionData implements SupplyDataInterface {

    private static ObservableList<Supply> supplyData = FXCollections.observableArrayList();

    @Override
    public void readData() {
        String selectQuery = "SELECT * FROM DB_Receipt_of_Medicines.Supply;";

        ArrayList<Supply> tempSupplies = new ArrayList<>();

        tempSupplies = Client.getInstance().executeQuery(EventNamespace.SELECT_QUERY, selectQuery, tempSupplies);

        supplyData.addAll(tempSupplies);
    }

    @Override
    public void update(Supply supply) {

        String updateQuery = "UPDATE DB_Receipt_of_Medicines.Supply SET admissionDate = '" + supply.getAdmissionDate().toString() +
                "', cost = '" + String.valueOf(supply.getCost()) + "', quantity = '" + String.valueOf(supply.getQuantity()) + "', " +
                "admissionCode = '" + String.valueOf(supply.getAdmissionCode()) + "' WHERE medCode = '" + String.valueOf(supply.getMedCode()) + "' && supplierCode = '" +
                String.valueOf(supply.getSupplierCode()) + "';";

        update(updateQuery);

    }

    @Override
    public void delete(Supply supply) {
        deleteRow = false;
        String deleteQuery = "DELETE FROM DB_Receipt_of_Medicines.Supply WHERE medCode = '" + String.valueOf(supply.getMedCode()) + "' && supplierCode = '" +
                String.valueOf(supply.getSupplierCode()) + "';";

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
