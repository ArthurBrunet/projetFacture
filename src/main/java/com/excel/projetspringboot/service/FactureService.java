package com.excel.projetspringboot.service;

import com.excel.projetspringboot.models.Facture;
import com.excel.projetspringboot.models.typeFacture.FactureFormation;
import com.excel.projetspringboot.models.typeFacture.FacturePrestation;
import com.excel.projetspringboot.repository.FactureFormationRepository;
import com.excel.projetspringboot.repository.FacturePrestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactureService {

    @Autowired
    FactureFormationRepository factureFormationRepository;

    @Autowired
    FacturePrestationRepository facturePrestationRepository;

    private List<Facture> getAllFacture() {
        List<FactureFormation> factureFormations = (List<FactureFormation>) factureFormationRepository.findAll();
        List<FacturePrestation> facturePrestations = (List<FacturePrestation>) facturePrestationRepository.findAll();

        List<Facture> list = null;
        list.addAll(factureFormations);
        list.addAll(facturePrestations);
        return list;
    }

    private Facture createFacture(FactureFormation factureFormation) {
        return factureFormationRepository.save(factureFormation);
    }

    private Facture createFacture(FacturePrestation facturePrestation) {
        return facturePrestationRepository.save(facturePrestation);
    }


}
