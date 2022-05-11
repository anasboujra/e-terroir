package com.site.eterroir.service;

import com.site.eterroir.dto.OrigineDto;

import java.util.List;

public interface OrigineService {
    OrigineDto create(OrigineDto origineDto);
    List<OrigineDto> list();
    OrigineDto get(Long id);
    Boolean delete(Long id);
    OrigineDto update(Long id, OrigineDto origineDto);
}
