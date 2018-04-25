package collectionsData.dataInterfaces;

import model.MedProd;

public interface MedProdDataInterface extends DataInterface {

    void update(MedProd medProd);

    void delete(MedProd medProd);

    void insert();

}
