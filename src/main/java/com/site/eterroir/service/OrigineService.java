package com.site.eterroir.service;

import com.site.eterroir.model.Origine;

import java.util.List;

public interface OrigineService {
    Origine create(Origine origine);
    List<Origine> list();
    Origine get(Long id);
    Boolean delete(Long id);
    Origine update(Long id, Origine origine) throws Exception;
}
