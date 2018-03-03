package collectionsData.dataInterfaces;

import javafx.collections.ObservableList;
import model.Supply;

public interface SupplyDataInterface extends DataInterface{

    ObservableList<Supply> getSupplyData();

    void update (Supply supply);

    void delete (Supply supply);

    void insert();
}
