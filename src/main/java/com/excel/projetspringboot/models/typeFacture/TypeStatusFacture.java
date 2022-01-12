package com.excel.projetspringboot.models.typeFacture;

public enum TypeStatusFacture {
    PAYE("PAYE"),ATTENTE("ATTENTE PAIEMENT"),NOT_PAID("PAS PAYE");

    TypeStatusFacture(String s) {}
}
