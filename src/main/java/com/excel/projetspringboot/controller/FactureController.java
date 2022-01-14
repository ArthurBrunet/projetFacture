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
            formMapperFacture.setRef(factureFormation.getRef());
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
        FormMapperFacture formMapperFacture = new FormMapperFacture();
        if (ref != null) {
            FacturePrestation facturePrestation1 = (FacturePrestation) factureService.getFactureByRef(ref);
            formMapperFacture.setDateEmise(facturePrestation1.getDateEmise().toString());
            formMapperFacture.setDatePaid(facturePrestation1.getDatePaid().toString());
            formMapperFacture.setIdClient(facturePrestation1.getClient().getId());
            formMapperFacture.setFormType("prestation");
            formMapperFacture.setRef(facturePrestation1.getRef());
            formMapperFacture.setId(facturePrestation1.getId());
            formMapperFacture.setHT(facturePrestation1.getHT());
            formMapperFacture.setTva(Double.toString(facturePrestation1.getTva().getSomme()));
            formMapperFacture.setTypeStatusFacture(facturePrestation1.getTypeStatusFacture().toString());
            formMapperFacture.setNature(facturePrestation1.getNature());
        }
        modelMap.addAttribute("listClient", listClient);
        modelMap.addAttribute("TVATypes", TypeTVA.values());
        modelMap.addAttribute("encaissementTypes", TypeEncaissementFacture.values());
        modelMap.addAttribute("statusFacture", TypeStatusFacture.values());
        modelMap.addAttribute("facture", formMapperFacture);
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
                FacturePrestation factureCreation = null;
                if (facture.getId() != null) {
                    factureCreation = (FacturePrestation) factureService.getFactureByRef(facture.getRef());
                    factureCreation.setHT(facture.getHT());
                    factureCreation.setId(facture.getId());
                    factureCreation.setTva(TypeTVA.valueOfLabel(Double.parseDouble(facture.getTva())));
                    factureCreation.setDateEmise(LocalDate.parse(facture.getDateEmise()));
                    factureCreation.setClient(client.get());
                    factureCreation.setNature(facture.getNature());
                    factureCreation.setDatePaid(LocalDate.parse(facture.getDatePaid()));
                    factureCreation.setTypeStatusFacture(TypeStatusFacture.valueOfLabel(facture.getTypeStatusFacture()));
                    factureCreation.setRef(facture.getRef());
                }else {
                    factureCreation = new FacturePrestation().toBuilder()
                            .client(client.get())
                            .tva(TypeTVA.valueOfLabel(Double.parseDouble(facture.getTva())))
                            .HT(facture.getHT())
                            .nature(facture.getNature())
                            .ref(facture.getRef())
                            .typeStatusFacture(TypeStatusFacture.valueOfLabel(facture.getTypeStatusFacture()))
                            .datePaid(LocalDate.parse(facture.getDatePaid()))
                            .dateEmise(LocalDate.parse(facture.getDateEmise()))
                            .build();
                }
                factureService.createFacture(factureCreation);
                return "redirect:/facture/factures";
            }else {
                FactureFormation factureFormation = null;
                if (facture.getId() != null) {
                    factureFormation = (FactureFormation) factureService.getFactureByRef(facture.getRef());
                    factureFormation.setHT(facture.getHT());
                    factureFormation.setId(facture.getId());
                    factureFormation.setNbCandidat(facture.getNbCandidat());
                    factureFormation.setTva(TypeTVA.valueOfLabel(Double.parseDouble(facture.getTva())));
                    factureFormation.setDateEmise(LocalDate.parse(facture.getDateEmise()));
                    factureFormation.setClient(client.get());
                    factureFormation.setNature(facture.getNature());
                    factureFormation.setDatePaid(LocalDate.parse(facture.getDatePaid()));
                    factureFormation.setTypeStatusFacture(TypeStatusFacture.valueOfLabel(facture.getTypeStatusFacture()));
                    factureFormation.setRef(facture.getRef());
                }else {
                    factureFormation = new FactureFormation().toBuilder()
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
                }
                factureService.createFacture(factureFormation);
                return "redirect:/facture/factures";
            }
        }else {
            return "redirect:/facture/factures";
        }
    }
}
