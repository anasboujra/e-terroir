package com.site.eterroir.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cooperative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String secteur;
    private String region;
    @Column(unique = true)
    private String email;
    @Column(name = "mot_de_passe")
    private String motDePasse;

    /*@OneToMany(
            mappedBy = "cooperative",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Produit> produits;
*/
}
