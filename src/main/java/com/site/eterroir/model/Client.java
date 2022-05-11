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
public class Client extends Utilisateur {

    @NotBlank(message = "Obligatoire")
    private String nom;
    @NotBlank(message = "Obligatoire")
    private String prenom;
    @Column(unique = true) @NotBlank(message = "Obligatoire")
    private String cin;
    @NotNull(message = "Obligatoire")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate dateNaissance;
    @NotBlank(message = "Obligatoire")
    private String adresse;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes = new ArrayList<>();
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Panier panier;

}
