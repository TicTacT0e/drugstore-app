package address;

import collectionsData.MedProdCollectionData;
import collectionsData.SuppliersCollectionData;
import collectionsData.SupplyCollectionData;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.MedProd;
import model.Suppliers;
import model.Supply;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MedProdOverviewController extends OverviewController implements Initializable {

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


    @FXML
    private TableView<Supply> supplyTable;
    @FXML
    private TableColumn<Supply, Number> supplyMedCodeColumn;
    @FXML
    private TableColumn<Supply, Number> supplySupplierCodeColumn;
    @FXML
    private TableColumn<Supply, LocalDate> addDateColumn;
    @FXML
    private TableColumn<Supply, Number> costColumn;
    @FXML
    private TableColumn<Supply, Number> quantityColumn;
    @FXML
    private TableColumn<Supply, Number> addCodeColumn;

    private SupplyCollectionData supplyCollectionData = new SupplyCollectionData();


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

    private SuppliersCollectionData suppliersCollectionData = new SuppliersCollectionData();


    public void setMedProdOverviewStage(Stage medProdOverviewStage) {
        this.medProdOverviewStage = medProdOverviewStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        medProdCollectionData.readData();
        medProdTable.setItems(medProdCollectionData.getMedProdData());

        medCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getMedCode()));
        nameMedColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNameMed()));
        indicationsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getIndications()));
        unitColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUnit()));
        quantityInPacColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQuantityInPac()));
        manufactNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getManufactName()));

        supplyCollectionData.readData();

        suppliersCollectionData.readData();

        /**
         * Слушатель для supply(showSupplyDetails)
         */
        medProdTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSupplyDetails(newValue));

        /**
         * Слушатель для suppliers(showSuppliersDetails)
         */
        medProdTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showSuppliersDetails(newValue));
    }

    private void showSuppliersDetails(MedProd medProd) {

        if (medProd != null) {

            int medCode = medProd.getMedCode();
            int supplierCode = supplyCollectionData.getSupplyData().get(--medCode).getSupplierCode();
            ObservableList<Suppliers> tempSupplierRow = FXCollections.observableArrayList();

            tempSupplierRow.add(suppliersCollectionData.getSuppliersData().get(--supplierCode));

            suppliersTable.setItems(tempSupplierRow);

            supplierCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getSupplierCode()));
            abbreviationColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAbbreviation()));
            fullTitleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFullTitle()));
            legalAddressColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLegalAddress()));
            phoneColumn.setCellValueFactory(cellData -> new ReadOnlyLongWrapper(cellData.getValue().getPhone()));
            headNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFullNameOfHead()));
        }

    }

    private void showSupplyDetails(MedProd medProd) {

        if (medProd != null) {

            int medCode = medProd.getMedCode();
            ObservableList<Supply> tempSupplyRow = FXCollections.observableArrayList();

            tempSupplyRow.add(supplyCollectionData.getSupplyData().get(--medCode));

            supplyTable.setItems(tempSupplyRow);

            supplyMedCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getMedCode()));
            supplySupplierCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getSupplierCode()));
            addDateColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getAdmissionDate()));
            costColumn.setCellValueFactory(cellData -> new ReadOnlyFloatWrapper(cellData.getValue().getCost()));
            quantityColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getQuantity()));
            addCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getAdmissionCode()));
        }
    }

    @FXML
    private void handleEditMedProd() {
        MedProd selectedMedProd = medProdTable.getSelectionModel().getSelectedItem();
        if (selectedMedProd != null) {
            boolean okClicked = showMedProdEditDialog(selectedMedProd);

            if (okClicked) {
                medProdTable.refresh();
                medProdCollectionData.update(selectedMedProd);
            }
        } else
            selectionError();
    }


    @FXML
    private void handleDeleteMedProd() {
        int selectedIndex = medProdTable.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            medProdCollectionData.delete(medProdTable.getSelectionModel().getSelectedItem());
            if (MedProdCollectionData.deleteRow) {
                medProdTable.getItems().remove(selectedIndex);
                medProdTable.refresh();
            }
        } else
            selectionError();
    }

    @FXML
    private void handleAddMedProd() {
        MedProd tempMedProd = new MedProd();
        boolean okClicked = showMedProdEditDialog(tempMedProd);
        if (okClicked) {
            int medCode = medProdCollectionData.getMedProdData().size();
            tempMedProd.setMedCode(++medCode);
            medProdCollectionData.getMedProdData().add(tempMedProd);
            medProdTable.refresh();
            medProdCollectionData.insert();
        }
    }

    @FXML
    private void handleViewSupply() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SupplyOverview.fxml"));
            Parent root = (Parent) loader.load();

            Stage viewSupplyStage = new Stage();
            viewSupplyStage.setTitle("Supply overview");
            viewSupplyStage.initModality(Modality.WINDOW_MODAL);
            viewSupplyStage.initOwner(medProdOverviewStage);
            Scene scene = new Scene(root, viewSupplyStage.getWidth(), viewSupplyStage.getHeight());
            viewSupplyStage.setResizable(false);
            viewSupplyStage.setScene(scene);

            SupplyOverviewController supplyOverviewController = loader.getController();
            supplyOverviewController.setSupplyOverviewStage(viewSupplyStage);

            viewSupplyStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleViewSuppliers() {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SuppliersOverview.fxml"));
            Parent root = (Parent) loader.load();

            Stage viewSuppliersStage = new Stage();
            viewSuppliersStage.setTitle("Suppliers overview");
            viewSuppliersStage.initModality(Modality.WINDOW_MODAL);
            viewSuppliersStage.initOwner(medProdOverviewStage);
            Scene scene = new Scene(root, viewSuppliersStage.getWidth(), viewSuppliersStage.getHeight());
            viewSuppliersStage.setResizable(false);
            viewSuppliersStage.setScene(scene);

            SuppliersOverviewController suppliersOverviewController = loader.getController();
            suppliersOverviewController.setSuppliersOverviewStage(viewSuppliersStage);

            viewSuppliersStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean showMedProdEditDialog(MedProd medProd) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MedProdEditDialog.fxml"));
            Parent root = (Parent) loader.load();

            Stage medProdEditDialogStage = new Stage();
            medProdEditDialogStage.setTitle("Edit production");
            medProdEditDialogStage.initModality(Modality.WINDOW_MODAL);
            medProdEditDialogStage.initOwner(medProdOverviewStage);
            Scene scene = new Scene(root, medProdEditDialogStage.getWidth(), medProdEditDialogStage.getHeight());
            medProdEditDialogStage.setResizable(false);
            medProdEditDialogStage.setScene(scene);

            MedProdEditDialogController medProdEditDialogController = loader.getController();
            medProdEditDialogController.setEditMedProdStage(medProdEditDialogStage);
            medProdEditDialogController.setMedProd(medProd);

            medProdEditDialogStage.showAndWait();

            return medProdEditDialogController.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}

