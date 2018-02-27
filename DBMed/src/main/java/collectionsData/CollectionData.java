package collectionsData;

import java.sql.Connection;

public class CollectionData {

    protected static Connection connection;

    public CollectionData() {
    }

    public static void setConnection(Connection connection) {
        CollectionData.connection = connection;
    }

}
