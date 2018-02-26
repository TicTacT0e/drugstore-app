package address;

import collectionsData.MedProdCollectionData;
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
import company.DBConnector;
import model.MedProd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ConnectWindowController implements Initializable {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button connectionButton;

    private boolean connection;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private Stage stage;

    private DBConnector dbConnector;

    private FXMLLoader loader;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        stage = new Stage();

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
        dbConnector = new DBConnector(userField.getText(), passField.getText());
        connection = dbConnector.startConnection();

        if (connection) {
            alert.setContentText("Database connected!");
            alert.showAndWait();
            try {
                stage.close();
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
        stage = new Stage();
        Parent root;
        loader = new FXMLLoader(getClass().getResource("/view/MedProdOverview.fxml"));
        root = (Parent) loader.load();
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(new Scene(root, stage.getWidth(), stage.getHeight()));
        stage.setTitle("MedProd");
        stage.show();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
