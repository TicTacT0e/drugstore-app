package address;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DBConnector;


public class Controller {

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passField;
    @FXML
    private Button connectionButton;

    private String username;
    private String password;
    private String message;
    private boolean connection;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    public void initialize() {

        alert.setTitle("Info");
        alert.setHeaderText(null);
        message = "";
        connection = false;

        connectionButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                username = userField.getText();
                password = passField.getText();

                DBConnector dbConnector = new DBConnector(username, password);
                connection = dbConnector.startConnection();

                if (connection)
                    message = "Database connected!";
                else message = "Incorrect username or password. Try again.";
                alert.setContentText(message);
                alert.showAndWait();

            }
        });
    }
}
