package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatierePremiere implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) @NotBlank(message = "Obligatoire")
    private String nom;

    @OneToMany(mappedBy = "matierePremiere")
    private List<ProduitMatiereAsso> produitMatiereAssos = new ArrayList<>();
}
