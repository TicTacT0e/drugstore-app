package collectionsData.dataInterfaces;

public interface DataInterface {

    void readData();

    void update(String updateQuery);

    void delete(String deleteQuery);

    void insert(String insertQuery);

}
