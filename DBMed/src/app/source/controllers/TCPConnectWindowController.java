package controllers;

import client.ClientThread;
import handle.EventNamespace;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.ParallelCamera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TCPConnectWindowController implements Initializable {

    @FXML
    private TextField userField;
    @FXML
    private TextField inetAddressField;
    @FXML
    private Button connectionButton;

    private Stage tcpConnectWindowStage;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private boolean connection;

    private boolean connectFlag = false;

    private static ClientThread clientThread;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alert.setTitle("Info");
        alert.setHeaderText(null);
        connection = false;

        connectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                connectFlag = checkFields();
                startConnect();
            }
        });

        userField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    connectFlag = checkFields();
                    startConnect();
                }
            }
        });

        inetAddressField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    connectFlag = checkFields();
                    startConnect();
                }
            }
        });
    }

    private void startConnect(){
        if(connectFlag)
            connect();
    }

    private boolean checkFields(){
        if(userField.getText().length() == 0 || inetAddressField.getText().length() == 0){
            alert.setContentText("Incorrect. Try again.");
            alert.showAndWait();
            return false;
        } else return true;
    }

    private void connect(){
        clientThread = new ClientThread();
        connection = clientThread.connect(inetAddressField.getText());

        if (connection) {
            alert.setContentText("Successful connection to the server!");
            alert.showAndWait();

            clientThread.clientRegistration(EventNamespace.REGISTRATION, userField.getText());
            tcpConnectWindowStage.close();
            try {
                startDBConnectWindow();
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            alert.setContentText("Incorrect IP address. Try again.");
            alert.showAndWait();
        }
    }

    private void startDBConnectWindow() throws IOException{
        Stage dbConnectStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DBConnectWindow.fxml"));
        Parent root = (Parent) loader.load();
        dbConnectStage.initModality(Modality.WINDOW_MODAL);
        dbConnectStage.setScene(new Scene(root, dbConnectStage.getWidth(), dbConnectStage.getHeight()));
        dbConnectStage.setResizable(false);
        dbConnectStage.setTitle("Connect to server database");
        dbConnectStage.show();

        DBConnectWindowController dbConnectWindowController = loader.getController();
        dbConnectWindowController.setDbConnectWindowStage(dbConnectStage);
        DBConnectWindowController.setClientThread(clientThread);
    }

    public void setTCPConnectWindowStage(Stage tcpConnectWindowStage) {
        this.tcpConnectWindowStage = tcpConnectWindowStage;
    }
}
