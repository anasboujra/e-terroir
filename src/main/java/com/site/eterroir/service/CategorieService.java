package com.site.eterroir.service;


import com.site.eterroir.model.Categorie;

import java.util.List;


public interface CategorieService {

    Categorie create(Categorie categorie);
    List<Categorie> list();
    Categorie get(Long id);
    Boolean delete(Long id);
    Categorie update(Long id, Categorie categorie) throws Exception;
}
