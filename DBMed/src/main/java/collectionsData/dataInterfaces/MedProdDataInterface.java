package collectionsData.dataInterfaces;

import javafx.collections.ObservableList;
import model.MedProd;

public interface MedProdDataInterface extends DataInterface {

    ObservableList<MedProd> getMedProdData();

    void update(MedProd medProd);

    void delete(MedProd medProd);

    void insert();

}
