package com.excel.projetspringboot.models.typeGlobal;

public enum TypeStatusFacture {

    PAYE("PAYE"),ATTENTE("ATTENTE PAIEMENT"),NOT_PAID("PAS PAYE");

    private final String value;

    TypeStatusFacture(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TypeStatusFacture valueOfLabel(String value) {
        for (TypeStatusFacture e : values()) {
            if (e.value.equals(value)) {
                return e;
            }
        }
        return null;
    }
}
