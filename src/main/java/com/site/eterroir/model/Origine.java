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
@AllArgsConstructor
@NoArgsConstructor
public class Origine implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) @NotBlank(message = "Obligatoire")
    private String nom;

    @OneToMany(mappedBy = "origine")
    private List<ProduitMatiereAsso> produitMatiereAssos = new ArrayList<>();
}
