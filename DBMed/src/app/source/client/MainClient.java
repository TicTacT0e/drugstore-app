package client;

import controllers.TCPConnectWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainClient extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/TCPConnectWindow.fxml"));
        Parent root = (Parent) loader.load();
        primaryStage.setTitle("Connect to server");
        Scene scene;
        primaryStage.setScene(scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.setResizable(false);

        TCPConnectWindowController tcpConnectWindowController = loader.getController();
        tcpConnectWindowController.setTCPConnectWindowStage(primaryStage);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
