package com.excel.projetspringboot.models.typeFacture;

import com.excel.projetspringboot.models.Facture;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@DiscriminatorValue("FORMATION")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class FactureFormation extends Facture {

    @NotNull
    private int nbCandidat;

}
