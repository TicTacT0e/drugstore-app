package company;

import address.ConnectWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/ConnectWindow.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("DrugStore");
        Scene scene;
        primaryStage.setScene(scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);

        ConnectWindowController connectWindowController = loader.getController();
        connectWindowController.setConnectWindowStage(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
