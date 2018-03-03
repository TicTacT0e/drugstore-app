package collectionsData;

import collectionsData.dataInterfaces.SupplyDataInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Supply;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SupplyCollectionData extends CollectionData implements SupplyDataInterface {

    private ObservableList<Supply> supplyData = FXCollections.observableArrayList();

    @Override
    public void readData() {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSetSupply = statement.executeQuery("SELECT * FROM db_receipt_of_medicines.supply");

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

    }

    @Override
    public void delete(Supply supply) {

    }

    @Override
    public void insert() {

    }

    @Override
    public ObservableList<Supply> getSupplyData() {
        return supplyData;
    }
}
