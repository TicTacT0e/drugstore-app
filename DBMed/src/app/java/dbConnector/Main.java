package dbConnector;

import controllers.DBConnectWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DBConnectWindow.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("DrugStore");
        Scene scene;
        primaryStage.setScene(scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);

        DBConnectWindowController dbConnectWindowController = loader.getController();
        dbConnectWindowController.setConnectWindowStage(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
