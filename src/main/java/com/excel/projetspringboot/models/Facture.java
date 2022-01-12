package com.excel.projetspringboot.models;

import com.excel.projetspringboot.models.typeFacture.TypeStatusFacture;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Data
public class Facture {

    private double HT;

    @Min(0)
    @Max(100)
    private int tva;

    private double ttc;

    private double tvaSomme;

    private LocalDate dateEmise;

    private LocalDate datePaid;

    private TypeStatusFacture typeStatusFacture = TypeStatusFacture.NOT_PAID;

    private String nature;

    private Client client;

    @Column(unique = true)
    private String ref;

}
