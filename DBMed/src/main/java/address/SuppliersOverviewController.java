package address;

import collectionsData.SuppliersCollectionData;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Suppliers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuppliersOverviewController extends OverviewController implements Initializable {

    private Stage suppliersOverviewStage;

    private SuppliersCollectionData suppliersCollectionData = new SuppliersCollectionData();

    @FXML
    private TableView<Suppliers> suppliersTable;
    @FXML
    private TableColumn<Suppliers, Number> supplierCodeColumn;
    @FXML
    private TableColumn<Suppliers, String> abbreviationColumn;
    @FXML
    private TableColumn<Suppliers, String> fullTitleColumn;
    @FXML
    private TableColumn<Suppliers, String> legalAddressColumn;
    @FXML
    private TableColumn<Suppliers, Number> phoneColumn;
    @FXML
    private TableColumn<Suppliers, String> headNameColumn;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        suppliersCollectionData.readData();
        suppliersTable.setItems(suppliersCollectionData.getSuppliersData());

        supplierCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getSupplierCode()));
        abbreviationColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAbbreviation()));
        fullTitleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFullTitle()));
        legalAddressColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLegalAddress()));
        phoneColumn.setCellValueFactory(cellData -> new ReadOnlyLongWrapper(cellData.getValue().getPhone()));
        headNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFullNameOfHead()));

    }

    @FXML
    private void handleEditSupplier() {
        Suppliers selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();
        if (selectedSupplier != null) {
            boolean okClicked = showSupplierEditDialog(selectedSupplier);

            if (okClicked) {
                suppliersTable.refresh();
                suppliersCollectionData.update(selectedSupplier);
            }
        } else
            selectionError();
    }

    @FXML
    private void handleDeleteSupplier() {
        int selectedIndex = suppliersTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            suppliersCollectionData.delete(suppliersTable.getSelectionModel().getSelectedItem());
            if (SuppliersCollectionData.deleteRow) {
                suppliersTable.getItems().remove(selectedIndex);
                suppliersTable.refresh();
            }
        } else
            selectionError();
    }

    @FXML
    private void handleAddSupplier() {
        Suppliers tempSupplier = new Suppliers();
        boolean okClicked = showSupplierEditDialog(tempSupplier);
        if (okClicked) {
            int supplierCode = suppliersCollectionData.getSuppliersData().size();
            tempSupplier.setSupplierCode(++supplierCode);
            suppliersCollectionData.getSuppliersData().add(tempSupplier);
            suppliersTable.refresh();
            suppliersCollectionData.insert();
        }
    }

    @FXML
    private void handleCancel() {
        suppliersOverviewStage.close();
    }

    private boolean showSupplierEditDialog(Suppliers suppliers) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplierEditDialog.fxml"));
            Parent root = (Parent) loader.load();

            Stage supplierEditDialogStage = new Stage();
            supplierEditDialogStage.setTitle("Edit supplier");
            supplierEditDialogStage.initModality(Modality.WINDOW_MODAL);
            supplierEditDialogStage.initOwner(suppliersOverviewStage);
            Scene scene = new Scene(root, supplierEditDialogStage.getWidth(), supplierEditDialogStage.getHeight());
            supplierEditDialogStage.setResizable(false);
            supplierEditDialogStage.setScene(scene);

            SupplierEditDialogController supplierEditDialogController = loader.getController();
            supplierEditDialogController.setSupplierEditStage(supplierEditDialogStage);
            supplierEditDialogController.setSupplier(suppliers);

            supplierEditDialogStage.showAndWait();

            return supplierEditDialogController.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setSuppliersOverviewStage(Stage suppliersOverviewStage) {
        this.suppliersOverviewStage = suppliersOverviewStage;
    }
}
