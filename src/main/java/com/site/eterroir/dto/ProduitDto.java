package com.site.eterroir.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ProduitDto {

    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private String unite;
    private Double quantite;

    private Map<Long, Long> idMatiereIdOrigine = new HashMap<>();

}
