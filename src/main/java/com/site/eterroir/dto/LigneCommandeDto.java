package com.site.eterroir.dto;

import lombok.Data;

@Data
public class LigneCommandeDto {
    private Long id;
    private Double quantite;
    private Double prixUnitaire;

    private Long idProduit;

    private Long idCommande;

    private Long idPanier;
}
