package com.site.eterroir.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cooperative extends Utilisateur {

    @Column(unique = true) @NotBlank(message = "Obligatoire")
    private String nom;
    @NotBlank(message = "Obligatoire")
    private String secteur;
    @NotBlank(message = "Obligatoire")
    private String region;

    @JsonIgnore
    @OneToMany(mappedBy = "cooperative")
    private List<Produit> produits = new ArrayList<>();

    public Cooperative(String email, String motDePasse, String numTele, String nom,
                       String secteur, String region) {
        setEmail(email);
        setMotDePasse(motDePasse);
        setNumTele(numTele);
        this.nom = nom;
        this.secteur = secteur;
        this.region = region;
    }
}
