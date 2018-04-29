package model;

import java.io.Serializable;

public class Suppliers implements Serializable, Model {

    private int supplierCode;
    private String abbreviation;
    private String fullTitle;
    private String legalAddress;
    private long phone;
    private String fullNameOfHead;

    public Suppliers() {
        this.supplierCode = 0;
        this.abbreviation = "";
        this.fullTitle = "";
        this.legalAddress = "";
        this.phone = 0;
        this.fullNameOfHead = "";
    }

    public Suppliers(int supplierCode, String abbreviation, String fullTitle, String legalAddress, long phone, String fullNameOfHead) {
        this.supplierCode = supplierCode;
        this.abbreviation = abbreviation;
        this.fullTitle = fullTitle;
        this.legalAddress = legalAddress;
        this.phone = phone;
        this.fullNameOfHead = fullNameOfHead;
    }


    public int getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(int supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getFullNameOfHead() {
        return fullNameOfHead;
    }

    public void setFullNameOfHead(String fullNameOfHead) {
        this.fullNameOfHead = fullNameOfHead;
    }
}
