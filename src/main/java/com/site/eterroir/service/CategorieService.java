package com.site.eterroir.service;

import com.site.eterroir.dto.CategorieDto;

import java.util.List;

public interface CategorieService {

    CategorieDto create(CategorieDto categorieDto);
    List<CategorieDto> list();
    CategorieDto get(Long id);
    Boolean delete(Long id);
    CategorieDto update(Long id, CategorieDto categorieDto) throws Exception;
}
