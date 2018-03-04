package address;

import collectionsData.SuppliersCollectionData;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyLongWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Suppliers;

import java.net.URL;
import java.util.ResourceBundle;

public class SuppliersOverviewController implements Initializable{

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
    private void handleEditSupplier(){

    }

    @FXML
    private void handleDeleteSupplier(){

    }

    @FXML
    private void handleAddSupplier(){

    }

    @FXML
    private void handleCancel(){
        suppliersOverviewStage.close();
    }

    public void setSuppliersOverviewStage(Stage suppliersOverviewStage) {
        this.suppliersOverviewStage = suppliersOverviewStage;
    }
}
