package com.site.eterroir.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true) @NotBlank(message = "Obligatoire") @Email
    private String email;
    @Column(name = "mot_de_passe") @NotBlank(message = "Obligatoire")
    private String motDePasse;
    @Column(name = "num_tele", unique = true) @NotBlank(message = "Obligatoire")
    private String numTele;

    @JsonIgnore
    public String getMotDePasse() {
        return motDePasse;
    }

    @JsonSetter
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
}
