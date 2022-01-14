package com.excel.projetspringboot.controller;

import com.excel.projetspringboot.formMapper.FormMapperFacture;
import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.models.Facture;
import com.excel.projetspringboot.models.typeFacture.FactureFormation;
import com.excel.projetspringboot.models.typeFacture.FacturePrestation;
import com.excel.projetspringboot.models.typeGlobal.TypeEncaissementFacture;
import com.excel.projetspringboot.models.typeGlobal.TypeStatusFacture;
import com.excel.projetspringboot.models.typeGlobal.TypeTVA;
import com.excel.projetspringboot.service.ClientService;
import com.excel.projetspringboot.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class FactureController {

    @Autowired
    FactureService factureService;

    @Autowired
    ClientService clientService;

    @GetMapping("/factures")
    public String getAllFacture(ModelMap modelMap) {
        List<Facture> factureList = factureService.getAllFacture();
        modelMap.addAttribute("factureList", factureList);
        return "facture/factures";
    }

    @GetMapping("/facture")
    public String getFacture(@RequestParam(name = "ref") String ref, ModelMap modelMap) {
        Facture facture = factureService.getFactureByRef(ref);
        modelMap.addAttribute("facture", facture);
        return "facture/facture";
    }

    @GetMapping("/facture/select")
    public String selectFacture() {
        return "facture/factureSelect";
    }

    @GetMapping("/facture/formation/form")
    public String formFactureFormation(ModelMap modelMap) {
        List<Client> listClient = clientService.getAll();
        modelMap.addAttribute("listClient", listClient);
        modelMap.addAttribute("TVATypes", TypeTVA.values());
        modelMap.addAttribute("encaissementTypes", TypeEncaissementFacture.values());
        modelMap.addAttribute("statusFacture", TypeStatusFacture.values());
        modelMap.addAttribute("facture", new FactureFormation());
        return "facture/factureForm";
    }

    @GetMapping("/facture/prestation/form")
    public String formFacturePrestation(ModelMap modelMap) {
        List<Client> listClient = clientService.getAll();
        FacturePrestation facturePrestation = new FacturePrestation();

        modelMap.addAttribute("listClient", listClient);
        modelMap.addAttribute("TVATypes", TypeTVA.values());
        modelMap.addAttribute("encaissementTypes", TypeEncaissementFacture.values());
        modelMap.addAttribute("statusFacture", TypeStatusFacture.values());
        modelMap.addAttribute("facture", facturePrestation);
        modelMap.addAttribute("factureClient", facturePrestation.getClient());

        return "facture/factureForm";
    }

    @PostMapping(value = {"/facture/create"})
    public ResponseEntity<Facture> createFacture(@ModelAttribute FormMapperFacture facture) {
        Long idClient = facture.getId();
        Optional<Client> client = clientService.getById(idClient);
        if (client.isPresent()){
            Facture factureToCreate = new Facture().toBuilder()
                    .client(client.get())
                    .tva(TypeTVA.valueofLabel(facture.getTva()))
                    .HT(facture.getHT())
                    .nature(facture.getNature())
                    .ref(facture.getRef())
                    .typeStatusFacture(TypeStatusFacture.valueOf(facture.getTypeStatusFacture()))
                    .build();
            return new ResponseEntity<>(factureToCreate, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
//        Facture factureCreate = factureService.create(facture);
//        return new ResponseEntity<>(factureCreate, HttpStatus.CREATED);
    }
}
