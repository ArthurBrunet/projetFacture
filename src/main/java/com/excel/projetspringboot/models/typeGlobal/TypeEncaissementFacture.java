package com.excel.projetspringboot.models.typeGlobal;

public enum TypeEncaissementFacture {
    VIREMENT("VIREMENT"),CB("CB"),ESPECE("ESPECE");

    private final String type;

    TypeEncaissementFacture(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
