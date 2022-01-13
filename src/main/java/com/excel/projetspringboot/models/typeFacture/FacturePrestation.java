package com.excel.projetspringboot.models.typeFacture;

import com.excel.projetspringboot.models.Facture;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Getter
@Setter
@DiscriminatorValue("PRESTATION")
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class FacturePrestation extends Facture {
}
