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

        medCodeColumn.setCellValueFactory(cellData -> new ReadOnlyIntegerWrapper(cellData.getValue().getMedCode()));
        medNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNameMed()));

        medProdCollectionData.readData();
        medProdTable.setItems(medProdCollectionData.getData());


    }

}

