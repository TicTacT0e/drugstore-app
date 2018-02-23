package model;

public class MedProd {

    private int medCode;
    private String nameMed;
    private String indications;
    private String unit;
    private int quanityInPac;
    private String manufactName;

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

    public int getQuanityInPac() {
        return quanityInPac;
    }

    public void setQuanityInPac(int quanityInPac) {
        this.quanityInPac = quanityInPac;
    }

    public String getManufactName() {
        return manufactName;
    }

    public void setManufactName(String manufactName) {
        this.manufactName = manufactName;
    }
}
