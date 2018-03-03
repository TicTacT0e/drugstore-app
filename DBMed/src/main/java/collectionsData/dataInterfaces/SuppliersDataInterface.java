package collectionsData.dataInterfaces;

import javafx.collections.ObservableList;
import model.Suppliers;

public interface SuppliersDataInterface  extends DataInterface{

    ObservableList<Suppliers> getSuppliersData();

    void update(Suppliers suppliers);

    void delete(Suppliers suppliers);

    void insert();
}
