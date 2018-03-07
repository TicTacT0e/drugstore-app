package collectionsData.dataInterfaces;

import model.Suppliers;

public interface SuppliersDataInterface extends DataInterface {


    void update(Suppliers suppliers);

    void delete(Suppliers suppliers);

    void insert();
}
