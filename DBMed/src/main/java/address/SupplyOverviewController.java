package address;

import collectionsData.SupplyCollectionData;
import javafx.beans.property.ReadOnlyFloatWrapper;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Supply;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SupplyOverviewController implements Initializable{

    private Stage supplyOverviewStage;

    private SupplyCollectionData supplyCollectionData = new SupplyCollectionData();

    @FXML
    private TableView<Supply> supplyTable;
    @FXML
    private TableColumn<Supply, Number> medCodeColumn;
    @FXML
    private TableColumn<Supply, Number> supplierCodeColumn;
    @FXML
    private TableColumn<Supply, LocalDate> addDateColumn;
    @FXML
    private TableColumn<Supply, Number> costColumn;
    @FXML
    private TableColumn<Supply, Number> quantityColumn;
    @FXML
    private TableColumn<Supply, Number> addCodeColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        supplyCollectionData.readData();
        supplyTable.setItems(supplyCollectionData.getSupplyData());

        medCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getMedCode()));
        supplierCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getSupplierCode()));
        addDateColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAdmissionDate()));
        costColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getCost()));
        quantityColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQuantity()));
        addCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getAdmissionCode()));

    }

    @FXML
    private void handleEditSupply(){

    }

    @FXML
    private void handleDeleteSupply(){

    }

    @FXML
    private void handleAddSupply(){

    }

    @FXML
    private void handleCancel(){
        supplyOverviewStage.close();
    }

    private boolean showSupplyEditDialog(Supply supply){

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplyEditDialog.fxml"));
            Parent root = (Parent) loader.load();

            Stage supplyEditStage = new Stage();
            supplyEditStage.setTitle("Supply edit");
            supplyEditStage.initModality(Modality.WINDOW_MODAL);
            supplyEditStage.initOwner(supplyOverviewStage);
            Scene scene = new Scene(root, supplyEditStage.getWidth(), supplyEditStage.getHeight());
            supplyEditStage.setResizable(false);
            supplyEditStage.setScene(scene);

            SupplyEditDialogController supplyEditDialogController = loader.getController();
            supplyEditDialogController.setSupplyEditDialogStage(supplyEditStage);
            supplyEditDialogController.setSupply(supply);

            supplyEditStage.showAndWait();

            return supplyEditDialogController.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }


    public void setSupplyOverviewStage(Stage supplyOverviewStage) {
        this.supplyOverviewStage = supplyOverviewStage;
    }
}
