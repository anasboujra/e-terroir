package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommande implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Obligatoire")
    private Double quantite;
    @Column(name = "prix_unitaire") @NotNull(message = "Obligatoire")
    private Double prixUnitaire;

    @ManyToOne
    private Produit produit;
    @ManyToOne
    private Commande commande;
    @ManyToOne
    private Panier panier;

}
