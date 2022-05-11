package com.site.eterroir.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitMatiereAsso implements Serializable {

    @EmbeddedId
    private ProduitMatiereKey id = new ProduitMatiereKey();

    @ManyToOne
    @MapsId("idProduit")
    private Produit produit;

    @ManyToOne
    @MapsId("idMatierePremiere")
    private MatierePremiere matierePremiere;

    @ManyToOne
    private Origine origine;

}
