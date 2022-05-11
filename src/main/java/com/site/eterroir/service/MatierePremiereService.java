package com.site.eterroir.service;

import com.site.eterroir.dto.MatierePremiereDto;

import java.util.List;

public interface MatierePremiereService {

    MatierePremiereDto create(MatierePremiereDto matiereDto);
    List<MatierePremiereDto> list();
    MatierePremiereDto get(Long id);
    Boolean delete(Long id);
    MatierePremiereDto update(Long id, MatierePremiereDto matiereDto);
}
