package address;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.MedProd;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MedProdEditDialogController implements Initializable {


    @FXML
    private Label medCodeEditLabel;
    @FXML
    private TextField medNameEditField;
    @FXML
    private TextField indicationsEditField;
    @FXML
    private TextField unitEditField;
    @FXML
    private TextField quanityInPacEditField;
    @FXML
    private TextField manufactNameEditField;

    private Stage editStage;

    private MedProd medProd;

    private boolean okClicked = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleOk() {

    }

    @FXML
    private void handleCancel() {
        editStage.close();
    }


    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setMedProd(MedProd medProd) {
        this.medProd = medProd;
    }
}
