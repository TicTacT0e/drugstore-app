package model;

import java.io.Serializable;

public class MedProd implements Serializable, Model{

    private int medCode;
    private String nameMed;
    private String indications;
    private String unit;
    private int quantityInPac;
    private String manufactName;

    public MedProd(){
        medCode = 0;
        nameMed = "";
        indications = "";
        unit = "";
        quantityInPac = 0;
        manufactName = "";

    }

    public MedProd(int medCode, String nameMed, String indications, String unit, int quantityInPac, String manufactName) {

        this.medCode = medCode;
        this.nameMed = nameMed;
        this.indications = indications;
        this.unit = unit;
        this.quantityInPac = quantityInPac;
        this.manufactName = manufactName;
    }


    public int getMedCode() {
        return medCode;
    }

    public void setMedCode(int medCode) {
        this.medCode = medCode;
    }

    public String getNameMed() {
        return nameMed;
    }

    public void setNameMed(String nameMed) {
        this.nameMed = nameMed;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getQuantityInPac() {
        return quantityInPac;
    }

    public void setQuantityInPac(int quantityInPac) {
        this.quantityInPac = quantityInPac;
    }

    public String getManufactName() {
        return manufactName;
    }

    public void setManufactName(String manufactName) {
        this.manufactName = manufactName;
    }
}