package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Supply implements Serializable, Model {

    private int medCode;
    private int supplierCode;
    private LocalDate admissionDate;
    private float cost;
    private int quantity;
    private int admissionCode;

    public Supply() {
        this.medCode = 0;
        this.supplierCode = 0;
        this.admissionDate = LocalDate.parse("0001-01-01");
        this.cost = 0;
        this.quantity = 0;
        this.admissionCode = 0;
    }

    public Supply(int medCode, int supplierCode, LocalDate admissionDate, float cost, int quantity, int admissionCode) {
        this.medCode = medCode;
        this.supplierCode = supplierCode;
        this.admissionDate = admissionDate;
        this.cost = cost;
        this.quantity = quantity;
        this.admissionCode = admissionCode;
    }

    public int getMedCode() {
        return medCode;
    }

    public void setMedCode(int medCode) {
        this.medCode = medCode;
    }

    public int getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(int supplierCode) {
        this.supplierCode = supplierCode;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getAdmissionCode() {
        return admissionCode;
    }

    public void setAdmissionCode(int admissionCode) {
        this.admissionCode = admissionCode;
    }
}
