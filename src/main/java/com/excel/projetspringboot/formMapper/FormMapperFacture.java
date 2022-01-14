package com.excel.projetspringboot.formMapper;

import java.time.LocalDate;

public class FormMapperFacture {

    private Long id;

    private Long idClient;

    private double HT;

    private String nature;

    private String ref;

    private String tva;

    private String typeStatusFacture;

    private String formType;

    private String datePaid;

    private String dateEmise;

    public String getDateEmise() {
        return dateEmise;
    }

    public void setDateEmise(String dateEmise) {
        this.dateEmise = dateEmise;
    }

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

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
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
