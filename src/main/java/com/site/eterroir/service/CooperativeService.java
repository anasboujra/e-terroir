package com.site.eterroir.service;

import com.site.eterroir.model.Cooperative;

import java.util.List;


public interface CooperativeService {
    Cooperative create(Cooperative cooperative);
    List<Cooperative> list();
    Cooperative get(Long id);
    Boolean delete(Long id);
    Cooperative update(Long id, Cooperative cooperative);
}
