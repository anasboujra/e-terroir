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
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private String unite;
    private Double quantite;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private Cooperative cooperative;
    @OneToMany(mappedBy = "produit", cascade = CascadeType.ALL)
    private List<ProduitMatiereAsso> produitMatieresAssos = new ArrayList<>();
    @OneToMany(mappedBy = "produit")
    private List<LigneCommande> lignes = new ArrayList<>();

}
