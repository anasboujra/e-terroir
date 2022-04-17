package com.site.eterroir.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "Commande")
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reference;
    @Column(name = "date_commande")
    private LocalDateTime dateCommande;
    @Column(name= "etat_commande")
    private String etatCommande;
    private String destination;

    /*@OneToMany
    private List<LigneCommande> lignes;*/
}
