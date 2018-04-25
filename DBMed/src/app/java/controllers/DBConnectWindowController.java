package controllers;

import collectionsData.MedProdCollectionData;
import dbConnector.DBConnector;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DBConnectWindowController implements Initializable {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button connectionButton;

    private boolean connection;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private Stage connectWindowStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        alert.setTitle("Info");
        alert.setHeaderText(null);
        connection = false;

        connectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                connect();
            }
        });

        userField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    connect();
                }
            }
        });
    }

    @FXML
    public void passFieldKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            connect();
        }
    }

    private void connect() {
        DBConnector dbConnector = new DBConnector(userField.getText(), passField.getText());
        connection = dbConnector.startConnection();

        if (connection) {
            alert.setContentText("Database connected!");
            alert.showAndWait();
            try {
                connectWindowStage.close();
                MedProdCollectionData.setConnection(dbConnector.getConnection());
                startMedProd();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            alert.setContentText("Incorrect username or password. Try again.");
            alert.showAndWait();
        }
    }

    private void startMedProd() throws IOException {
        Stage medProdStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MedProdOverview.fxml"));
        Parent root = (Parent) loader.load();
        medProdStage.initModality(Modality.WINDOW_MODAL);
        medProdStage.setScene(new Scene(root, medProdStage.getWidth(), medProdStage.getHeight()));
        medProdStage.setResizable(false);
        medProdStage.setTitle("MedProd");
        medProdStage.show();

        MedProdOverviewController medProdOverviewController = loader.getController();
        medProdOverviewController.setMedProdOverviewStage(connectWindowStage);
    }

    public void setConnectWindowStage(Stage connectWindowStage) {
        this.connectWindowStage = connectWindowStage;
    }
}
