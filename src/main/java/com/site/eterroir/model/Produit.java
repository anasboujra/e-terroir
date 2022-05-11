package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Obligatoire")
    private String nom;
    @NotBlank(message = "Obligatoire")
    private String description;
    @NotNull(message = "Obligatoire")
    private Double prix;
    @NotBlank(message = "Obligatoire")
    private String unite;
    @NotNull(message = "Obligatoire")
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
