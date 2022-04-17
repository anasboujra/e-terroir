package com.site.eterroir.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class MatierePremiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nom;
    private String description;

    @OneToMany(mappedBy = "matierePremiere")
    private List<ProduitMatiereAsso> produitMatiereAsso = new ArrayList<>();
}
