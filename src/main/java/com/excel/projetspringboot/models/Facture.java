package com.excel.projetspringboot.models;

import com.excel.projetspringboot.models.typeGlobal.TypeStatusFacture;
import com.excel.projetspringboot.models.typeGlobal.TypeTVA;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_FACTURE")
@DiscriminatorValue("MERE")
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@SuperBuilder(toBuilder = true)
public class Facture implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double HT;

    private TypeTVA tva;

    private double ttc;

    private double tvaSomme;

    private LocalDate dateEmise;

    private LocalDate datePaid;

    private TypeStatusFacture typeStatusFacture = TypeStatusFacture.NOT_PAID;

    private String nature;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(unique = true)
    private String ref;

    public Facture() {
    }
}
