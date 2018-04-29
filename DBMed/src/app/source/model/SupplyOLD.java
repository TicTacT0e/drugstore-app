package model;

import javafx.beans.property.*;

import java.io.Serializable;
import java.time.LocalDate;

public class SupplyOLD implements Serializable{

    private final IntegerProperty medCode;
    private final IntegerProperty supplierCode;
    private final ObjectProperty<LocalDate> admissionDate;
    private final FloatProperty cost;
    private final IntegerProperty quantity;
    private final IntegerProperty admissionCode;

    public SupplyOLD(){
        this.medCode = new SimpleIntegerProperty(0);
        this.supplierCode = new SimpleIntegerProperty(0);
        this.admissionDate = new SimpleObjectProperty<LocalDate>(LocalDate.of(0001, 01, 01));
        this.cost = new SimpleFloatProperty(0);
        this.quantity = new SimpleIntegerProperty(0);
        this.admissionCode = new SimpleIntegerProperty(0);
    }

    public SupplyOLD(int medCode, int supplierCode, LocalDate admissionDate, float cost, int quantity, int admissionCode){
        this.medCode = new SimpleIntegerProperty(medCode);
        this.supplierCode = new SimpleIntegerProperty(supplierCode);
        this.admissionDate = new SimpleObjectProperty<LocalDate>(admissionDate);
        this.cost = new SimpleFloatProperty(cost);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.admissionCode = new SimpleIntegerProperty(admissionCode);
    }

    public void setMedCode(int medCode) {
        this.medCode.set(medCode);
    }

    public void setSupplierCode(int supplierCode){
        this.supplierCode.set(supplierCode);
    }

    public void setAdmissionDate(LocalDate admissionDate){
        this.admissionDate.set(admissionDate);
    }

    public void setCost(float cost){
        this.cost.set(cost);
    }

    public void setQuantity(int quantity){
        this.quantity.set(quantity);
    }

    public void setAdmissionCode(int admissionCode){
        this.admissionCode.set(admissionCode);
    }

    public int getMedCode() {
        return medCode.get();
    }

    public IntegerProperty medCodeProperty() {
        return medCode;
    }

    public int getSupplierCode() {
        return supplierCode.get();
    }

    public IntegerProperty supplierCodeProperty() {
        return supplierCode;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate.get();
    }

    public ObjectProperty<LocalDate> admissionDateProperty() {
        return admissionDate;
    }

    public float getCost() {
        return cost.get();
    }

    public FloatProperty costProperty() {
        return cost;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }

    public int getAdmissionCode() {
        return admissionCode.get();
    }

    public IntegerProperty admissionCodeProperty() {
        return admissionCode;
    }
}