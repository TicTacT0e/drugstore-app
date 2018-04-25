package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Suppliers;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplierEditDialogController extends EditDialog implements Initializable{

    @FXML
    private TextField supplierCodeEditField;
    @FXML
    private TextField abbreviationEditField;
    @FXML
    private TextField fullTitleEditField;
    @FXML
    private TextField legalAddressEditField;
    @FXML
    private TextField phoneEditField;
    @FXML
    private TextField nameOfHeadEditField;

    private Stage supplierEditStage;

    private Suppliers supplier;

    private boolean okClicked = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleOk(){

        if(isInputValid()){
            supplier.setSupplierCode(Integer.parseInt(supplierCodeEditField.getText()));
            supplier.setAbbreviation(abbreviationEditField.getText());
            supplier.setFullTitle(fullTitleEditField.getText());
            supplier.setLegalAddress(legalAddressEditField.getText());
            supplier.setPhone(Long.parseLong(phoneEditField.getText()));
            supplier.setFullNameOfHead(nameOfHeadEditField.getText());

            okClicked = true;
            supplierEditStage.close();
        }

    }

    @FXML
    private void handleCancel(){
        supplierEditStage.close();
    }

    private boolean isInputValid(){

        String errorMessage = "";

        if (supplierCodeEditField.getText() == null || supplierCodeEditField.getText().length() == 0)
            errorMessage += "No valid supplier code.\n";
        else {
            try {
                Integer.parseInt(supplierCodeEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid supplier code. It must be an integr value.\n";
            }
        }
        if (abbreviationEditField.getText() == null || abbreviationEditField.getText().length() == 0)
            errorMessage += "No valid abbreviation.\n";
        if (fullTitleEditField.getText() == null || fullTitleEditField.getText().length() == 0)
            errorMessage += "No valid full title.\n";
        if (legalAddressEditField.getText() == null || legalAddressEditField.getText().length() == 0)
            errorMessage += "No valid legal controllers.\n";
        if (phoneEditField.getText() == null || phoneEditField.getText().length() == 0)
            errorMessage += "No valid phone.\n";
        else {
            try {
                Long.parseLong(phoneEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid phone. It must be an long value.\n";
            }
        }
        if (nameOfHeadEditField.getText() == null || nameOfHeadEditField.getText().length() == 0)
            errorMessage += "Na valid name of head.\n";

        return errorAlert(errorMessage, supplierEditStage);
    }

    public void setSupplierEditStage(Stage supplierEditStage) {
        this.supplierEditStage = supplierEditStage;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;

        supplierCodeEditField.setText(String.valueOf(supplier.getSupplierCode()));
        abbreviationEditField.setText(supplier.getAbbreviation());
        fullTitleEditField.setText(supplier.getFullTitle());
        legalAddressEditField.setText(supplier.getLegalAddress());
        phoneEditField.setText(String.valueOf(supplier.getPhone()));
        nameOfHeadEditField.setText(supplier.getFullNameOfHead());
    }

    public boolean isOkClicked(){
        return okClicked;
    }
}
