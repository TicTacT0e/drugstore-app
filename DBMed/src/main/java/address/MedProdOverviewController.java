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
import javafx.scene.layout.AnchorPane;
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
    private TableColumn<MedProd, Number> quanityInPacColumn;
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
        quanityInPacColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQuanityInPac()));
        manufactNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getManufactName()));

        //medProdTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> handleEditMedProd(newValue)));

    }

    @FXML
    private void handleDeleteMedProd() {
        int selectedIndex = medProdTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            medProdTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Product Selected");
            alert.setContentText("Please select a product in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleEditMedProd() {
        int selectedIndex = medProdTable.getSelectionModel().getSelectedIndex();

        showMedProdEditDialog(medProdTable.getItems().get(selectedIndex));
    }

    @FXML
    private void handleAddMedProd() {

    }

    public boolean showMedProdEditDialog(MedProd medProd){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MedProdEditDialog.fxml"));
            Parent root = (Parent) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit production");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(medProdOverviewStage);
            Scene scene = new Scene(root, dialogStage.getWidth(), dialogStage.getHeight());
            dialogStage.setScene(scene);

            MedProdEditDialogController medProdEditDialogController = loader.getController();
            medProdEditDialogController.setEditStage(medProdOverviewStage);
            medProdEditDialogController.setMedProd(medProd);

            dialogStage.showAndWait();

            return medProdEditDialogController.isOkClicked();
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

}

