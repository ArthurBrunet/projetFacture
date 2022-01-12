package com.excel.projetspringboot.models;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Entity
public class Client {
    @Id
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
