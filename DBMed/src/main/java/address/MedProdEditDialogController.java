package address;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MedProd;

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
    private TextField quantityInPacEditField;
    @FXML
    private TextField manufactNameEditField;

    private Stage editStage;

    private MedProd medProd;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleOk() {
        editStage.close();
    }

    @FXML
    private void handleCancel() {
        editStage.close();
    }


    public void setEditStage(Stage editStage) {
        this.editStage = editStage;
    }


    public void setMedProd(MedProd medProd) {
        this.medProd = medProd;
    }

    public void setEditLabel(int numEditProd){
        medCodeEditLabel.setText(String.valueOf(numEditProd));
    }
}
