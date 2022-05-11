package com.site.eterroir.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class LigneCommandeDto implements Serializable {
    private Long id;
    @NotNull(message = "Obligatoire")
    private Double quantite;
    @NotNull(message = "Obligatoire")
    private Double prixUnitaire;

    private Long idProduit;

    private Long idCommande;

    private Long idPanier;
}
