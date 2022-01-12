package com.excel.projetspringboot.models.typeFacture;

import com.excel.projetspringboot.models.Facture;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FacturePrestation extends Facture {
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
