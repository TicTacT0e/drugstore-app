package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("DrugStore");
        primaryStage.setScene(new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }

    public static void main(String[] args){
      launch(args);
/*
      DBConnector dbConnector = new DBConnector();

      try (Statement statement = dbConnector.getConnection().createStatement()){

          ResultSet resultSet = statement.executeQuery("SELECT medCode, nameMed FROM db_receipt_of_medicines.medprod");

          while (resultSet.next()){
              System.out.println(resultSet.getInt(1));
              System.out.println(resultSet.getString(2));
          }

      }catch (SQLException ex){
          ex.printStackTrace();
      }
*/
    }
}
