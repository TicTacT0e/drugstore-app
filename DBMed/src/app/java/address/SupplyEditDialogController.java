package address;

import collectionsData.MedProdCollectionData;
import collectionsData.SuppliersCollectionData;
import model.MedProd;
import model.Suppliers;
import utils.DateUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Supply;

import java.net.URL;
import java.util.ResourceBundle;

public class SupplyEditDialogController extends EditDialog implements Initializable{

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

    private MedProdCollectionData medProdCollectionData = new MedProdCollectionData();
    private SuppliersCollectionData suppliersCollectionData = new SuppliersCollectionData();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleOk(){

        if (isInputValid()){
            supply.setMedCode(Integer.parseInt(supplyMedCodeEditField.getText()));
            supply.setSupplierCode(Integer.parseInt(supplySupplierCodeEditField.getText()));
            supply.setAdmissionDate(DateUtil.parse(addDateEditField.getText()));
            supply.setCost(Float.parseFloat(costEditField.getText()));
            supply.setQuantity(Integer.parseInt(quantityEditField.getText()));
            supply.setAdmissionCode(Integer.parseInt(addCodeEditField.getText()));

            okClicked = true;
            supplyEditDialogStage.close();
        }

    }

    @FXML
    private void handleCancel(){
        supplyEditDialogStage.close();
    }

    private boolean isInputValid(){

        boolean flagMedCodeExists = false;
        boolean flagSupplierCodeExists = false;

        String errorMessage = "";

        if(supplyMedCodeEditField.getText() == null || supplyMedCodeEditField.getText().length() == 0)
            errorMessage += "No valid medicine code.\n";
        else {
            try{
                Integer.parseInt(supplyMedCodeEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid medicine code. It must be an integer value.\n";
            }
        }

        for (MedProd medProd : medProdCollectionData.getMedProdData()){
            if(Integer.parseInt(supplyMedCodeEditField.getText()) == medProd.getMedCode())
                flagMedCodeExists = true;
        }
        for (Suppliers suppliers : suppliersCollectionData.getSuppliersData()){
            if(Integer.parseInt(supplySupplierCodeEditField.getText()) == suppliers.getSupplierCode())
                flagSupplierCodeExists = true;
        }

        if (!flagMedCodeExists)
            errorMessage += "This medicin code doesn't exist.\n";
        if (!flagSupplierCodeExists)
            errorMessage += "This supplier code doesn't exist.\n";

        if(supplySupplierCodeEditField.getText() == null || supplySupplierCodeEditField.getText().length() == 0)
            errorMessage += "No valid supplier code.\n";
        else {
            try {
                Integer.parseInt(supplySupplierCodeEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid supplier code. It must be an integer value.\n";
            }
        }

        if(addDateEditField.getText() == null || addDateEditField.getText().length() == 0)
            errorMessage += "No valid admission date.\n";
        else {
            if (!DateUtil.validDate(addDateEditField.getText())){
                errorMessage += "No valid admission date. Use the format yyyy-MM-dd.\n";
            }
        }

        if(costEditField.getText() == null || costEditField.getText().length() == 0)
            errorMessage += "No valid cost.\n";
        else {
            try{
                Float.parseFloat(costEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid cost. It must be an float value.\n";
            }
        }

        if (quantityEditField.getText() == null || quantityEditField.getText().length() == 0){
            errorMessage += "No valid quantity.\n";
        }else {
            try {
                Integer.parseInt(quantityEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid quantity. It must be an integer value.\n";
            }
        }

        if (addCodeEditField.getText() == null || addCodeEditField.getText().length() == 0)
            errorMessage += "No valid admission code.\n";
        else {
            try {
                Integer.parseInt(addCodeEditField.getText());
            }catch (NumberFormatException e){
                errorMessage += "No valid admission code. It must be an integer value.\n";
            }
        }

        return errorAlert(errorMessage, supplyEditDialogStage);
    }

    public void setSupplyEditDialogStage(Stage supplyEditDialogStage) {
        this.supplyEditDialogStage = supplyEditDialogStage;
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

    public boolean isOkClicked(){
        return okClicked;
    }

}
