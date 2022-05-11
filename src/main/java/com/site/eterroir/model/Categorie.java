package com.site.eterroir.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "categorie")
public class Categorie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Obligatoire")
    private String nom;

    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;
}
