package collectionsData.dataInterfaces;

import model.Supply;

public interface SupplyDataInterface extends DataInterface {

    void update(Supply supply);

    void delete(Supply supply);

    void insert();
}
