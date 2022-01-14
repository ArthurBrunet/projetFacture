package com.excel.projetspringboot.formMapper;

public class FormMapperFacture {

    private Long id;

    private double HT;

    private String nature;

    private String ref;

    private String tva;

    private String typeStatusFacture;

    private String formType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getHT() {
        return HT;
    }

    public void setHT(double HT) {
        this.HT = HT;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getTypeStatusFacture() {
        return typeStatusFacture;
    }

    public void setTypeStatusFacture(String typeStatusFacture) {
        this.typeStatusFacture = typeStatusFacture;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }
}
