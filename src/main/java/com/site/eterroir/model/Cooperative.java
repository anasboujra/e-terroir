package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cooperative extends Utilisateur {

    private String nom;
    private String secteur;
    private String region;
    private String adresse;
    @Column(name = "num_tele")
    private String numTele;
    @OneToMany(mappedBy = "cooperative")
    private List<Produit> produits = new ArrayList<>();

    public Cooperative(String email, String motDePasse) {
        setEmail(email);
        setMotDePasse(motDePasse);
    }
}
