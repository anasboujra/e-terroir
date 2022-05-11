package com.site.eterroir.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class Admin extends Utilisateur {

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

    public Admin(String email, String motDePasse, String numTele, String nom,
                 String prenom, String cin, LocalDate dateNaissance) {
        setEmail(email);
        setMotDePasse(motDePasse);
        setNumTele(numTele);
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
    }
}
