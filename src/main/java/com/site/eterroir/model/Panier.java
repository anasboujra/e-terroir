package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@ManyToMany
    @JoinTable(
            name = "panier_produits",
            joinColumns = @JoinColumn(name="panier_id"),
            inverseJoinColumns = @JoinColumn(name = "produit_id")
            )
    private List<Produit> produits;*/
}
