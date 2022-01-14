package com.excel.projetspringboot.repository;

import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.models.Facture;
import com.excel.projetspringboot.models.typeFacture.FactureFormation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface FactureRepository extends CrudRepository<Facture, Long> {
    Facture findByRef(String ref);
    List<Facture> findByClient(Client client);
    List<Facture> findByDateEmiseBetween(LocalDate date1, LocalDate date2);
}
