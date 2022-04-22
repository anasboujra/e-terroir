package com.site.eterroir.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class CommandeDto {
    private Long id;
    private LocalDateTime date;
    private String etat;
    private Long prixTotal;
    private Long idClient;
    private List<Long> idLignes = new ArrayList<>();
    private Long idPanier;
}
