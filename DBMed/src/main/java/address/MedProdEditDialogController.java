package address;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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

    private Stage editMedProdStage;

    private MedProd medProd;

    private boolean okClicked = false;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleOk() {

        if(isInputValid()){
            medProd.setNameMed(medNameEditField.getText());
            medProd.setIndications(indicationsEditField.getText());
            medProd.setUnit(unitEditField.getText());
            medProd.setQuantityInPac(Integer.parseInt(quantityInPacEditField.getText()));
            medProd.setManufactName(manufactNameEditField.getText());

            okClicked = true;
            editMedProdStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        editMedProdStage.close();
    }


    public void setEditMedProdStage(Stage editMedProdStage) {
        this.editMedProdStage = editMedProdStage;
    }


    public void setMedProd(MedProd medProd) {
        this.medProd = medProd;

        medNameEditField.setText(medProd.getNameMed());
        indicationsEditField.setText(medProd.getIndications());
        unitEditField.setText(medProd.getUnit());
        quantityInPacEditField.setText(String.valueOf(medProd.getQuantityInPac()));
        manufactNameEditField.setText(medProd.getManufactName());
    }

    public void setEditLabel(int numEditProd){
        medCodeEditLabel.setText(String.valueOf(numEditProd));
    }

    private boolean isInputValid(){

        String errorMessage = "";

        if(medNameEditField.getText() == null || medNameEditField.getText().length() == 0)
            errorMessage += "No valid medicine production name.\n";
        if(indicationsEditField.getText() == null || indicationsEditField.getText().length() == 0)
            errorMessage += "No valid indications.\n";
        if(unitEditField.getText() == null || unitEditField.getText().length() == 0)
            errorMessage += "No valid unit.\n";
        if(quantityInPacEditField.getText() == null || quantityInPacEditField.getText().length() == 0) {
            errorMessage += "No valid quantity.\n";
        }else {
            try {
                Integer.parseInt(quantityInPacEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid quantity. It must be an integer number.\n";
            }
        }
        if(manufactNameEditField.getText() == null || manufactNameEditField.getText().length() == 0)
            errorMessage += "No valid manufacturer name.\n";

        if (errorMessage.length() == 0) {
            return true;
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(editMedProdStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
