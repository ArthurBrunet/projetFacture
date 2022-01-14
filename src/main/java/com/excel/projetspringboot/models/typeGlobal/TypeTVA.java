package com.excel.projetspringboot.models.typeGlobal;

import java.lang.reflect.Type;
import java.util.Arrays;

public enum TypeTVA {
    NORMAL(20.0),INTER(10.0),REDUIT(5.5),PARTICULIER(2.1);

    private final Double somme;

    TypeTVA(double somme) {
        this.somme = somme;
    }

    public double getSomme() {
        return somme;
    }

    public static TypeTVA valueOfLabel(double somme) {
        for (TypeTVA e : values()) {
            if (e.somme.equals(somme)) {
                return e;
            }
        }
        return null;
    }
}
