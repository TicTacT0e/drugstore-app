package controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

class EditDialog {

    public boolean errorAlert (String errorMessage, Stage stage){
        if (errorMessage.length() == 0){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
