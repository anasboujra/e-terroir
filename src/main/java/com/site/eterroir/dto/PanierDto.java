package com.site.eterroir.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PanierDto {
    private Long id;

    private List<Long> idLignes = new ArrayList<>();

    private Long idClient;
}
