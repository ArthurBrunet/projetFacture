package com.excel.projetspringboot.models.typeFacture;

import com.excel.projetspringboot.models.Facture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class FactureFormation extends Facture {

    @NotNull
    private int nbCandidat;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
