package collectionsData.dataInterfaces;

import javafx.collections.ObservableList;
import model.MedProd;

public interface MedProdDataTable extends DataTable {

    ObservableList<MedProd> getMedProdData();

    void update();

    void delete(int indexMedCode);

}
