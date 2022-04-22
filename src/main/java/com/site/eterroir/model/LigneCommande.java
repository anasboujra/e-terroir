package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantite;
    @Column(name = "prix_unitaire")
    private Double prixUnitaire;
    @ManyToOne
    private Produit produit;
    @ManyToOne
    private Commande commande;
    @ManyToOne
    private Panier panier;
}
