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
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class FactureController {

    @Autowired
    FactureService factureService;

    @Autowired
    ClientService clientService;

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
    public String formFactureFormation(@RequestParam(name = "ref", required = false) String ref, ModelMap modelMap) {
        List<Client> listClient = clientService.getAll();
        FormMapperFacture formMapperFacture = new FormMapperFacture();
        if (ref != null) {
            FactureFormation factureFormation = (FactureFormation) factureService.getFactureByRef(ref);
            formMapperFacture.setDateEmise(factureFormation.getDateEmise().toString());
            formMapperFacture.setDatePaid(factureFormation.getDatePaid().toString());
            formMapperFacture.setIdClient(factureFormation.getClient().getId());
            formMapperFacture.setFormType("formation");
            formMapperFacture.setId(factureFormation.getId());
            formMapperFacture.setHT(factureFormation.getHT());
            formMapperFacture.setTva(Double.toString(factureFormation.getTva().getSomme()));
            formMapperFacture.setTypeStatusFacture(factureFormation.getTypeStatusFacture().toString());
            formMapperFacture.setNature(factureFormation.getNature());
        }
        modelMap.addAttribute("listClient", listClient);
        modelMap.addAttribute("TVATypes", TypeTVA.values());
        modelMap.addAttribute("encaissementTypes", TypeEncaissementFacture.values());
        modelMap.addAttribute("statusFacture", TypeStatusFacture.values());
        modelMap.addAttribute("facture", formMapperFacture);
        modelMap.addAttribute("typeFacture","formation");
        return "facture/factureForm";
    }

    @GetMapping("/facture/prestation/form")
    public String formFacturePrestation(@RequestParam(name = "ref", required = false) String ref, ModelMap modelMap) {
        List<Client> listClient = clientService.getAll();
        FacturePrestation facturePrestation = new FacturePrestation();

        modelMap.addAttribute("listClient", listClient);
        modelMap.addAttribute("TVATypes", TypeTVA.values());
        modelMap.addAttribute("encaissementTypes", TypeEncaissementFacture.values());
        modelMap.addAttribute("statusFacture", TypeStatusFacture.values());
        modelMap.addAttribute("facture", new FormMapperFacture());
        modelMap.addAttribute("factureClient", facturePrestation.getClient());
        modelMap.addAttribute("typeFacture","prestation");

        return "facture/factureForm";
    }

    @GetMapping("/factures")
    public String getFacturesByDate(@RequestParam(name = "date") String stringDateSearch, ModelMap modelMap) {
        LocalDate date = LocalDate.parse(stringDateSearch);
        LocalDate firstDayOfMonth = date.withDayOfMonth(1);
        LocalDate lastDayOfMonth = firstDayOfMonth.plusMonths(1).minusDays(1);
        List<Facture> factureList =  factureService.findByDateEmiseBetween(firstDayOfMonth,lastDayOfMonth);
        Double sumTTC = factureList.stream().mapToDouble(Facture::getTtc).sum();
        Double sumHT = factureList.stream().mapToDouble(Facture::getHT).sum();
        modelMap.addAttribute("sumTTC", sumTTC);
        modelMap.addAttribute("sumHT", sumHT);
        modelMap.addAttribute("factureList", factureList);
        return "facture/factures";
    }

    @PostMapping(value = {"/facture/create"})
    public String createFacture(@ModelAttribute FormMapperFacture facture) {
        Long idClient = facture.getIdClient();
        Optional<Client> client = clientService.getById(idClient);
        if (client.isPresent()){
            if (facture.getFormType().equals("prestation")){
                Facture factureToCreate = new FacturePrestation().toBuilder()
                        .id(facture.getId())
                        .client(client.get())
                        .tva(TypeTVA.valueOfLabel(Double.parseDouble(facture.getTva())))
                        .HT(facture.getHT())
                        .nature(facture.getNature())
                        .ref(facture.getRef())
                        .typeStatusFacture(TypeStatusFacture.valueOfLabel(facture.getTypeStatusFacture()))
                        .datePaid(LocalDate.parse(facture.getDatePaid()))
                        .dateEmise(LocalDate.parse(facture.getDateEmise()))
                        .build();
                factureService.createFacture(factureToCreate);
                return "redirect:/facture/factures";
            }else {
                Facture factureToCreate = new FactureFormation().toBuilder()
                        .id(facture.getId())
                        .client(client.get())
                        .tva(TypeTVA.valueOfLabel(Double.parseDouble(facture.getTva())))
                        .HT(facture.getHT())
                        .nature(facture.getNature())
                        .ref(facture.getRef())
                        .typeStatusFacture(TypeStatusFacture.valueOfLabel(facture.getTypeStatusFacture()))
                        .datePaid(LocalDate.parse(facture.getDatePaid()))
                        .dateEmise(LocalDate.parse(facture.getDateEmise()))
                        .build();
                factureService.createFacture(factureToCreate);
                return "redirect:/facture/factures";
            }
        }else {
            return "redirect:/facture/factures";
        }
    }
}
