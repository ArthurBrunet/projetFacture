package com.excel.projetspringboot.service;

import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.models.Facture;
import com.excel.projetspringboot.repository.FactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FactureService {

    @Autowired
    FactureRepository factureRepository;

    public List<Facture> getAllFacture() {
        return (List<Facture>) factureRepository.findAll();
    }

    public Facture createFacture(Facture facture) {
        double HT = facture.getHT();
        double TVA = facture.getTva().getSomme();
        double tvaSomme = (TVA / 100) * HT;
        facture.setTvaSomme(tvaSomme);
        facture.setTtc(HT+tvaSomme);
        return factureRepository.save(facture);
    }

    public List<Facture> getAllFactureByClient(Client client) {
        return factureRepository.findByClient(client);
    }

    public Facture getFactureByRef(String ref) {
        return factureRepository.findByRef(ref);
    }

    public List<Facture> findByDateEmiseBetween(LocalDate firstDayOfMonth, LocalDate lastDayOfMonth) {
        return factureRepository.findByDateEmiseBetween(firstDayOfMonth,lastDayOfMonth);
    }

}
