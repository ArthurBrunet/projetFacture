package com.excel.projetspringboot;

import com.excel.projetspringboot.models.Client;
import com.excel.projetspringboot.models.Facture;
import com.excel.projetspringboot.models.typeFacture.FactureFormation;
import com.excel.projetspringboot.models.typeGlobal.TypeStatusFacture;
import com.excel.projetspringboot.models.typeGlobal.TypeTVA;
import com.excel.projetspringboot.service.ClientService;
import com.excel.projetspringboot.service.FactureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProjetSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetSpringBootApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(FactureService factureService, ClientService clientService) {
        return args -> {
            Client client = clientService.create(new Client().builder()
                    .email("client@gmail.com")
                    .nom("Alexandre")
                    .prenom("toto")
                    .fullAdress("10 rue de l'ancienne Prison, Rouen")
                    .build());
            factureService.create(
                    new FactureFormation()
                            .toBuilder()
                            .tva(TypeTVA.NORMAL)
                            .nature("bdza")
                            .ref("resf")
                            .client(client)
                            .typeStatusFacture(TypeStatusFacture.PAYE)
                            .nbCandidat(20)
                            .build()
            );

            List<Facture> facturesClient = factureService.getAllFactureByClient(client);
        };
    }

}
