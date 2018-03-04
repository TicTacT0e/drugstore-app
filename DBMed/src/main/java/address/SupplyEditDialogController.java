package address;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Supply;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplyEditDialogController implements Initializable{

    @FXML
    private TextField supplyMedCodeEditField;
    @FXML
    private TextField supplySupplierCodeEditField;
    @FXML
    private TextField addDateEditField;
    @FXML
    private TextField costEditField;
    @FXML
    private TextField quantityEditField;
    @FXML
    private TextField addCodeEditField;

    private Stage supplyEditDialogStage;

    private Supply supply;

    private boolean okClicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleOk(){

    }

    @FXML
    private void handleCancel(){
        supplyEditDialogStage.close();
    }

    public void setSupply(Supply supply) {
        this.supply = supply;

        supplyMedCodeEditField.setText(String.valueOf(supply.getMedCode()));
        supplySupplierCodeEditField.setText(String.valueOf(supply.getSupplierCode()));
        addDateEditField.setText(supply.getAdmissionDate().toString());
        costEditField.setText(String.valueOf(supply.getCost()));
        quantityEditField.setText(String.valueOf(supply.getQuantity()));
        addCodeEditField.setText(String.valueOf(supply.getAdmissionCode()));

    }

    private boolean isInputValid(){
        String errorMesage = "";

        if(supplyMedCodeEditField.getText() == null || supplyMedCodeEditField.getText().length() == 0)
            errorMesage += "No valid medicine code.\n";
        else {
            try{
                Integer.parseInt(supplyMedCodeEditField.getText());
            }catch (NumberFormatException e){
                errorMesage += "No valid medicine code. It must be an integer value.\n";
            }
        }
        if(supplySupplierCodeEditField.getText() == null || supplySupplierCodeEditField.getText().length() == 0)
            errorMesage += "No valid supplier code.\n";
        else {
            try {
                Integer.parseInt(supplySupplierCodeEditField.getText());
            }catch (NumberFormatException e){
                errorMesage += "No valid supplier code. It must be an integer value.\n";
            }
        }
        if(addDateEditField.getText() == null || addDateEditField.getText().length() == 0)
            errorMesage += "No valid admission date.\n";
        else {
            try {

            }catch () {

            }
        }

        return false;
    }

    public void setSupplyEditDialogStage(Stage supplyEditDialogStage) {
        this.supplyEditDialogStage = supplyEditDialogStage;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

}
