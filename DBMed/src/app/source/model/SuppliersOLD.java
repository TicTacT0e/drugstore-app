package model;

import javafx.beans.property.*;

import java.io.Serializable;

public class SuppliersOLD implements Serializable {

    private final IntegerProperty supplierCode;
    private final StringProperty abbreviation;
    private final StringProperty fullTitle;
    private final StringProperty legalAddress;
    private final LongProperty phone;
    private final StringProperty fullNameOfHead;

    public SuppliersOLD(){
        this.supplierCode = new SimpleIntegerProperty(0);
        this.abbreviation = new SimpleStringProperty("");
        this.fullTitle = new SimpleStringProperty("");
        this.legalAddress = new SimpleStringProperty("");
        this.phone = new SimpleLongProperty(0);
        this.fullNameOfHead = new SimpleStringProperty("");
    }

    public SuppliersOLD (int supplierCode, String abbreviation, String fullTitle, String legalAddress, long phone, String fullNameOfHead){
        this.supplierCode = new SimpleIntegerProperty(supplierCode);
        this.abbreviation = new SimpleStringProperty(abbreviation);
        this.fullTitle = new SimpleStringProperty(fullTitle);
        this.legalAddress = new SimpleStringProperty(legalAddress);
        this.phone = new SimpleLongProperty(phone);
        this.fullNameOfHead = new SimpleStringProperty(fullNameOfHead);
    }

    public void setSupplierCode(int supplierCode){
        this.supplierCode.set(supplierCode);
    }

    public void setAbbreviation(String abbreviation){
        this.abbreviation.set(abbreviation);
    }

    public void setFullTitle(String fullTitle){
        this.fullTitle.set(fullTitle);
    }

    public void setLegalAddress(String legalAddress){
        this.legalAddress.set(legalAddress);
    }

    public void setPhone(long phone){
        this.phone.setValue(phone);
    }

    public void setFullNameOfHead(String fullNameOfHead){
        this.fullNameOfHead.set(fullNameOfHead);
    }

    public int getSupplierCode() {
        return supplierCode.get();
    }

    public IntegerProperty supplierCodeProperty() {
        return supplierCode;
    }

    public String getAbbreviation() {
        return abbreviation.get();
    }

    public StringProperty abbreviationProperty() {
        return abbreviation;
    }

    public String getFullTitle() {
        return fullTitle.get();
    }

    public StringProperty fullTitleProperty() {
        return fullTitle;
    }

    public String getLegalAddress() {
        return legalAddress.get();
    }

    public StringProperty legalAddressProperty() {
        return legalAddress;
    }

    public long getPhone() {
        return phone.get();
    }

    public LongProperty phoneProperty() {
        return phone;
    }

    public String getFullNameOfHead() {
        return fullNameOfHead.get();
    }

    public StringProperty fullNameOfHeadProperty() {
        return fullNameOfHead;
    }
}