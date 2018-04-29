package collectionsData;

import client.Client;
import collectionsData.dataInterfaces.SuppliersDataInterface;
import handle.EventNamespace;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Suppliers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SuppliersCollectionData extends CollectionData implements SuppliersDataInterface{

    private static ObservableList<Suppliers> suppliersData = FXCollections.observableArrayList();

    @Override
    public void readData(){
        String selectQuery = "SELECT * FROM DB_Receipt_of_Medicines.Suppliers;";

        ArrayList<Suppliers> tempSuppliers = new ArrayList<>();

        tempSuppliers = Client.getInstance().executeQuery(EventNamespace.SELECT_QUERY, selectQuery, tempSuppliers);

        suppliersData.addAll(tempSuppliers);
    }

    @Override
    public void update(Suppliers suppliers){
        String updateQuery = "UPDATE DB_Receipt_of_Medicines.Suppliers SET abbreviation = '" + suppliers.getAbbreviation() + "', fullTitle = '" + suppliers.getFullTitle() +
                "', legalAddress = '" + suppliers.getLegalAddress() + "', phone = '" + String.valueOf(suppliers.getPhone()) + "', " +
                "fullNameOfHead = '" + suppliers.getFullNameOfHead() + "' WHERE supplierCode = '" + String.valueOf(suppliers.getSupplierCode()) + "';";

        update(updateQuery);
    }

    @Override
    public void delete(Suppliers suppliers){
        String deleteQuery = "DELETE FROM DB_Receipt_of_Medicines.Suppliers WHERE supplierCode = '" + String.valueOf(suppliers.getSupplierCode()) + "';";

        delete(deleteQuery);
    }

    @Override
    public void insert(){
        String insertQuery = "INSERT INTO DB_Receipt_of_Medicines.Suppliers (supplierCode, abbreviation, fullTitle, legalAddress, phone, fullNameOfHead) " +
                "VALUES (" + String.valueOf(suppliersData.get(suppliersData.size() - 1).getSupplierCode()) + ", '" + suppliersData.get(suppliersData.size() - 1).getAbbreviation() +
                "', '" + suppliersData.get(suppliersData.size() - 1).getFullTitle() + "', '" + suppliersData.get(suppliersData.size() - 1).getLegalAddress() +
                "', " + String.valueOf(suppliersData.get(suppliersData.size() - 1).getPhone()) + ", " +
                "'" + suppliersData.get(suppliersData.size() - 1).getFullNameOfHead() + "');";

        insert(insertQuery);
    }

    public ObservableList<Suppliers> getSuppliersData(){
        return suppliersData;
    }


}
