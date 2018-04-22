package address;

import javafx.scene.control.Alert;

class OverviewController {


    protected void selectionError() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Product Selected");
        alert.setContentText("Please select a product in the table.");

        alert.showAndWait();
    }

}
