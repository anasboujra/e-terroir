package com.site.eterroir.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ProduitDto implements Serializable {

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

    private String categorie;

    private String cooperative;

    private Map<String, String> matiereOrigine = new HashMap<>();

}
