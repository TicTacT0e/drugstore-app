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
    private final IntegerProperty quanityInPac;
    private final StringProperty manufactName;

    public MedProd() {
        medCode = null;
        nameMed = null;
        indications = null;
        unit = null;
        quanityInPac = null;
        manufactName = null;
    }

    public MedProd(int medCode, String nameMed, String indications, String unit, int quanityInPac, String manufactName) {

        this.medCode = new SimpleIntegerProperty(medCode);
        this.nameMed = new SimpleStringProperty(nameMed);
        this.indications = new SimpleStringProperty(indications);
        this.unit = new SimpleStringProperty(unit);
        this.quanityInPac = new SimpleIntegerProperty(quanityInPac);
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

    public void setQuanityInPac(int quanityInPac) {
        this.quanityInPac.set(quanityInPac);
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

    public int getQuanityInPac() {
        return quanityInPac.get();
    }

    public IntegerProperty quanityInPacProperty() {
        return quanityInPac;
    }

    public String getManufactName() {
        return manufactName.get();
    }

    public StringProperty manufactNameProperty() {
        return manufactName;
    }
}