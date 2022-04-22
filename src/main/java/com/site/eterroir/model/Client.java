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
public class Client extends Utilisateur {

    private String nom;
    private String prenom;
    private String adresse;
    @Column(name = "num_tele")
    private String numTele;
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<>();
    @OneToOne(mappedBy = "client")
    private Panier panier;

    public Client(String email, String motDePasse) {
        setEmail(email);
        setMotDePasse(motDePasse);
    }
}
