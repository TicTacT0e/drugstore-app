package address;

import collectionsData.MedProdCollectionData;
import javafx.beans.property.ReadOnlyIntegerWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private TableColumn<MedProd, String> medNameColumn;

    @FXML
    private Label medCodeLabel;
    @FXML
    private Label medNameLabel;
    @FXML
    private Label indicationsLabel;
    @FXML
    private Label unitLabel;
    @FXML
    private Label quanityInPacLabel;
    @FXML
    private Label manufactNameLabel;

    private MedProdCollectionData medProdCollectionData = new MedProdCollectionData();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        medProdCollectionData.readData();
        medProdTable.setItems(medProdCollectionData.getData());

        medCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getMedCode()));
        medNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNameMed()));

        showMedProdDetails(null);

        medProdTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showMedProdDetails(newValue));

    }

    private void showMedProdDetails(MedProd medProd){

        if (medProd != null){
            medCodeLabel.setText(String.valueOf(medProd.getMedCode()));
            medNameLabel.setText(medProd.getNameMed());
            indicationsLabel.setText(medProd.getIndications());
            unitLabel.setText(medProd.getIndications());
            quanityInPacLabel.setText(String.valueOf(medProd.getQuanityInPac()));
            manufactNameLabel.setText(medProd.getManufactName());
        }else {
            medCodeLabel.setText("");
            medNameLabel.setText("");
            indicationsLabel.setText("");
            unitLabel.setText("");
            quanityInPacLabel.setText("");
            manufactNameLabel.setText("");
        }

    }

}

