package com.site.eterroir.service.implementation;

import com.site.eterroir.dto.OrigineDto;
import com.site.eterroir.model.Origine;
import com.site.eterroir.repository.OrigineRepo;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.OrigineService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class OrigineServiceImpl implements OrigineService {

    private final OrigineRepo origineRepo;

    @Override
    public OrigineDto create(OrigineDto origineDto) {
        if(SecurityService.isAdmin()) {
            Origine origine = dtoToEntity(origineDto);
            return entityToDto(origineRepo.save(origine));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public List<OrigineDto> list() {
        List<Origine> origines = origineRepo.findAll();
        List<OrigineDto> dtoList = new ArrayList<>();
        for(Origine origine : origines){
            dtoList.add(entityToDto(origine));
        }
        return dtoList;
    }

    @Override
    public OrigineDto get(Long id) {
        return entityToDto(origineRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        if(SecurityService.isAdmin()) {
            origineRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public OrigineDto update(Long id, OrigineDto origineDto) {
        if(SecurityService.isAdmin()) {
            Origine dbOrigine = origineRepo.findById(id).get();
            Origine origine = dtoToEntity(origineDto);
            if(origine.getNom() != null)
                dbOrigine.setNom(origine.getNom());
            return entityToDto(origineRepo.save(dbOrigine));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }


    // DTO conversion:

    private OrigineDto entityToDto(Origine origine){
        OrigineDto origineDto = new OrigineDto();
        origineDto.setId(origine.getId());
        origineDto.setNom(origine.getNom());

        return origineDto;
    };

    private Origine dtoToEntity(OrigineDto origineDto){
        Origine origine = new Origine();
        origine.setId(origineDto.getId());
        origine.setNom(origineDto.getNom());

        return origine;
    };

}
