package address;

import collectionsData.MedProdCollectionData;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.MedProd;

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

    }

    @FXML
    private void handleDeleteMedProd(){
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

}

