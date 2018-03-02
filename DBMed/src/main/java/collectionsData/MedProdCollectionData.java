package collectionsData;

import collectionsData.dataInterfaces.MedProdDataTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MedProd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class MedProdCollectionData extends CollectionData implements MedProdDataTable {

    private ObservableList<MedProd> medProdsData = FXCollections.observableArrayList();

    private int initialSize;
    private int finalSize;


    public void readData() {

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSetMedProd = statement.executeQuery("SELECT * FROM db_receipt_of_medicines.medprod");

            while (resultSetMedProd.next()) {
                medProdsData.add(new MedProd(resultSetMedProd.getInt(1), resultSetMedProd.getString(2),
                        resultSetMedProd.getString(3), resultSetMedProd.getString(4),
                        resultSetMedProd.getInt(5), resultSetMedProd.getString(6)));
            }

            initialSize = medProdsData.size();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Максимально кривое исполнение, надо переделать по человечески
     */
    public void update(){

        finalSize = medProdsData.size();

        ArrayList<String> update = new ArrayList<>();
        for (int i = 0; i < initialSize; i++){
            update.add("UPDATE db_receipt_of_medicines.medprod SET nameMed = '" + medProdsData.get(i).getNameMed() + "', indications = '" + medProdsData.get(i).getIndications() +
                    "', unit = '" + medProdsData.get(i).getUnit() + "', quanityInPac = '" + String.valueOf(medProdsData.get(i).getQuantityInPac()) + "', " +
                            "manufactName = '" + medProdsData.get(i).getManufactName() + "' WHERE medCode = '" + String.valueOf(medProdsData.get(i).getMedCode()) + "';");
        }

        ArrayList<String> insert = new ArrayList<>();
        for (int i = initialSize; i < finalSize; i++){
            insert.add("INSERT INTO db_receipt_of_medicines.medprod (medCode, nameMed, indications, unit, quanityInPac, manufactName) " +
                    "VALUES (" + String.valueOf(medProdsData.get(i).getMedCode()) + ", '" + medProdsData.get(i).getNameMed() + "', '" + medProdsData.get(i).getIndications() + "', '" +
                    medProdsData.get(i).getUnit() + "', " + String.valueOf(medProdsData.get(i).getQuantityInPac()) + ", '" + medProdsData.get(i).getManufactName() + "');");
        }

        for (String line : update){
            System.out.println(line);
        }
        for (String line : insert){
            System.out.println(line);
        }

        try (Statement statement = connection.createStatement()) {

            for (String updateSqlQuery : update)
                statement.executeUpdate(updateSqlQuery);

            for (String insertSqlQuery : insert)
                statement.execute(insertSqlQuery);
        }catch (SQLException e){
            e.printStackTrace();
        }
        
    }

    /**
     * EEE BOY
     */
    public void fillTestData() {
        medProdsData.add(new MedProd(1, "СhopChop", " ", " ", 0, " "));
        medProdsData.add(new MedProd(2, "SlapSlap", " ", " ", 0, " "));
    }

    public ObservableList<MedProd> getMedProdData() {
        return medProdsData;
    }

}
