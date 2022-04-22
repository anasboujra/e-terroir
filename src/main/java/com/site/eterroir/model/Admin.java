package com.site.eterroir.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
public class Admin extends Utilisateur {

    private String nom;
    private String prenom;

    public Admin(String email, String motDePasse) {
        setEmail(email);
        setMotDePasse(motDePasse);
    }
}
