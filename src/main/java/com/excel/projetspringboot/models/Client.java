package com.excel.projetspringboot.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nom;

    private String prenom;

    private String fullAdress;

    @Email
    @NotNull
    private String email;

    private String tel;

    public Client() {

    }
}
