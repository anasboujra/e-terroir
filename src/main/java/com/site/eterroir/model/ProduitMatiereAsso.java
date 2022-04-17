package com.site.eterroir.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitMatiereAsso {

    @EmbeddedId
    ProduitMatiereKey id = new ProduitMatiereKey();

    @ManyToOne
    @MapsId("idProduit")
    @JoinColumn(name = "id_produit")
    Produit produit;

    @ManyToOne
    @MapsId("idMatierePremiere")
    @JoinColumn(name = "id_matiere")
    MatierePremiere matierePremiere;

    @ManyToOne
    private Origine origine;

}
