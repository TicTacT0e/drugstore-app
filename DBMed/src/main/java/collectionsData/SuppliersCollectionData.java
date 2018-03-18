package collectionsData;

import collectionsData.dataInterfaces.SuppliersDataInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Suppliers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SuppliersCollectionData extends CollectionData implements SuppliersDataInterface{

    private static ObservableList<Suppliers> suppliersData = FXCollections.observableArrayList();

    @Override
    public void readData(){

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSetSuppliers = statement.executeQuery("SELECT * FROM DB_Receipt_of_Medicines.Suppliers");

            while (resultSetSuppliers.next()){
                suppliersData.add(new Suppliers(resultSetSuppliers.getInt(1), resultSetSuppliers.getString(2), resultSetSuppliers.getString(3),
                        resultSetSuppliers.getString(4), resultSetSuppliers.getLong(5), resultSetSuppliers.getString(6)));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
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
