package com.excel.projetspringboot.models.typeGlobal;

public enum TypeStatusFacture {

    PAYE("PAYE"),ATTENTE("ATTENTE PAIEMENT"),NOT_PAID("PAS PAYE");

    private final String value;

    TypeStatusFacture(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
