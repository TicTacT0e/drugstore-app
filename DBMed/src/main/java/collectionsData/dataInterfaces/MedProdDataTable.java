package collectionsData.dataInterfaces;

import javafx.collections.ObservableList;
import model.MedProd;

public interface MedProdDataTable extends DataTable {

    ObservableList<MedProd> getMedProdData();

    void update(MedProd medProd);

    void delete(MedProd medProd);

    void insert();

}
