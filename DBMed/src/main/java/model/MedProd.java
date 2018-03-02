package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedProd {

    private final IntegerProperty medCode;
    private final StringProperty nameMed;
    private final StringProperty indications;
    private final StringProperty unit;
    private final IntegerProperty quantityInPac;
    private final StringProperty manufactName;

    public MedProd() {
        medCode = new SimpleIntegerProperty(0);
        nameMed = new SimpleStringProperty("");
        indications = new SimpleStringProperty("");
        unit = new SimpleStringProperty("");
        quantityInPac = new SimpleIntegerProperty(0);
        manufactName = new SimpleStringProperty("");
    }

    public MedProd(int medCode, String nameMed, String indications, String unit, int quantityInPac, String manufactName) {

        this.medCode = new SimpleIntegerProperty(medCode);
        this.nameMed = new SimpleStringProperty(nameMed);
        this.indications = new SimpleStringProperty(indications);
        this.unit = new SimpleStringProperty(unit);
        this.quantityInPac = new SimpleIntegerProperty(quantityInPac);
        this.manufactName = new SimpleStringProperty(manufactName);
    }

    public void setMedCode(int medCode) {
        this.medCode.set(medCode);
    }

    public void setNameMed(String nameMed) {
        this.nameMed.set(nameMed);
    }

    public void setIndications(String indications) {
        this.indications.set(indications);
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    public void setQuantityInPac(int quantityInPac) {
        this.quantityInPac.set(quantityInPac);
    }

    public void setManufactName(String manufactName) {
        this.manufactName.set(manufactName);
    }

    public int getMedCode() {
        return medCode.get();
    }

    public IntegerProperty medCodeProperty() {
        return medCode;
    }

    public String getNameMed() {
        return nameMed.get();
    }

    public StringProperty nameMedProperty() {
        return nameMed;
    }

    public String getIndications() {
        return indications.get();
    }

    public StringProperty indicationsProperty() {
        return indications;
    }

    public String getUnit() {
        return unit.get();
    }

    public StringProperty unitProperty() {
        return unit;
    }

    public int getQuantityInPac() {
        return quantityInPac.get();
    }

    public IntegerProperty quantityInPacProperty() {
        return quantityInPac;
    }

    public String getManufactName() {
        return manufactName.get();
    }

    public StringProperty manufactNameProperty() {
        return manufactName;
    }
}