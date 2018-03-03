package collectionsData;

import collectionsData.dataInterfaces.SuppliersDataInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Suppliers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SuppliersCollectionData extends CollectionData implements SuppliersDataInterface{

    private ObservableList<Suppliers> suppliersData = FXCollections.observableArrayList();

    @Override
    public void readData(){

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSetSuppliers = statement.executeQuery("SELECT * FROM db_receipt_of_medicines.suppliers");

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

    }

    @Override
    public void delete(Suppliers suppliers){

    }

    @Override
    public void insert(){

    }

    @Override
    public ObservableList<Suppliers> getSuppliersData(){
        return suppliersData;
    }


}
