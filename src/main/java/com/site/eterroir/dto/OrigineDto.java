package com.site.eterroir.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class OrigineDto implements Serializable {

    private Long id;
    @NotBlank(message = "Obligatoire")
    private String nom;

}
