package com.excel.projetspringboot.models.typeGlobal;

public enum TypeTVA {
    NORMAL(20),INTER(10),REDUIT(5.5),PARTICULIER(2.1);

    private final double somme;

    TypeTVA(double somme) {
        this.somme = somme;
    }

    public double getSomme() {
        return somme;
    }
}
