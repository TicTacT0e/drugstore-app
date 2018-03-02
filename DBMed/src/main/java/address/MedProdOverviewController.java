package address;

import collectionsData.MedProdCollectionData;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MedProd;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MedProdOverviewController implements Initializable {

    @FXML
    private TableView<MedProd> medProdTable;
    @FXML
    private TableColumn<MedProd, Number> medCodeColumn;
    @FXML
    private TableColumn<MedProd, String> nameMedColumn;
    @FXML
    private TableColumn<MedProd, String> indicationsColumn;
    @FXML
    private TableColumn<MedProd, String> unitColumn;
    @FXML
    private TableColumn<MedProd, Number> quantityInPacColumn;
    @FXML
    private TableColumn<MedProd, String> manufactNameColumn;

    private MedProdCollectionData medProdCollectionData = new MedProdCollectionData();

    private Stage medProdOverviewStage;


    public void setMedProdOverviewStage(Stage medProdOverviewStage) {
        this.medProdOverviewStage = medProdOverviewStage;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        medProdCollectionData.readData();
        medProdTable.setItems(medProdCollectionData.getData());

        medCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getMedCode()));
        nameMedColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNameMed()));
        indicationsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIndications()));
        unitColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUnit()));
        quantityInPacColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQuantityInPac()));
        manufactNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getManufactName()));
    }

    @FXML
    private void handleDeleteMedProd() {
        int selectedIndex = medProdTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            medProdTable.getItems().remove(selectedIndex);
        } else
            selectionError();
    }

    @FXML
    private void handleEditMedProd() {
        int selectedIndex = medProdTable.getSelectionModel().getSelectedIndex();
        System.out.println(selectedIndex);

        if(selectedIndex >= 0)
            showMedProdEditDialog(medProdTable.getSelectionModel().getSelectedItem(), selectedIndex);
        else
            selectionError();
    }

    @FXML
    private void handleAddMedProd() {

    }

    private void showMedProdEditDialog(MedProd medProd, int selectedIndex){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MedProdEditDialog.fxml"));
            Parent root = (Parent) loader.load();

            Stage editDialogStage = new Stage();
            editDialogStage.setTitle("Edit production");
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(medProdOverviewStage);
            Scene scene = new Scene(root, editDialogStage.getWidth(), editDialogStage.getHeight());
            editDialogStage.setResizable(false);
            editDialogStage.setScene(scene);

            MedProdEditDialogController medProdEditDialogController = loader.getController();
            medProdEditDialogController.setEditStage(editDialogStage);
            medProdEditDialogController.setEditLabel(++selectedIndex);
            medProdEditDialogController.setMedProd(medProd);

            editDialogStage.showAndWait();


        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void selectionError(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setHeaderText("No Product Selected");
        alert.setContentText("Please select a product in the table.");

        alert.showAndWait();
    }

}

