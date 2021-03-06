package com.site.eterroir.service;

import com.site.eterroir.dto.ProduitDto;
import com.site.eterroir.specification.ProduitSpec;

import java.util.List;

public interface ProduitService {

    ProduitDto create(ProduitDto produitDto);
    List<ProduitDto> list(ProduitSpec produitSpec);
    ProduitDto get(Long id);
    Boolean delete(Long id);
    ProduitDto update(Long id, ProduitDto produitDto);

}
