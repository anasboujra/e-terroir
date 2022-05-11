package com.site.eterroir.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class PanierDto implements Serializable {

    private Long id;

    private List<Long> idLignes = new ArrayList<>();

}
