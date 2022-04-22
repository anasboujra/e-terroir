package com.site.eterroir.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String etat;
    @Column(name= "prix_total")
    private Long prixTotal;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> lignes = new ArrayList<>();
    @OneToOne
    private Panier panier;
}
