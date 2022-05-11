package com.site.eterroir.service.implementation;

import com.site.eterroir.dto.CategorieDto;
import com.site.eterroir.model.Categorie;
import com.site.eterroir.repository.CategorieRepo;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.CategorieService;
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
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepo categorieRepo;

    @Override
    public CategorieDto create(CategorieDto categorieDto) {
        if(SecurityService.isAdmin()) {
            Categorie categorie = dtoToEntity(categorieDto);
            return entityToDto(categorieRepo.save(categorie));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public List<CategorieDto> list() {
        List<Categorie> categories = categorieRepo.findAll();
        List<CategorieDto> dtoList = new ArrayList<>();
        for(Categorie categorie : categories){
            dtoList.add(entityToDto(categorie));
        }
        return dtoList;
    }

    @Override
    public CategorieDto get(Long id) {
        return entityToDto(categorieRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        if(SecurityService.isAdmin()) {
            categorieRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public CategorieDto update(Long id, CategorieDto categorieDto) {
        if(SecurityService.isAdmin()) {
            Categorie dbCategorie = categorieRepo.findById(id).get();
            Categorie categorie = dtoToEntity(categorieDto);
            if(categorie.getNom() != null)
                dbCategorie.setNom(categorie.getNom());
            return entityToDto(categorieRepo.save(dbCategorie));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }


    // DTO conversion:

    private CategorieDto entityToDto(Categorie categorie){
        CategorieDto categorieDto = new CategorieDto();
        categorieDto.setId(categorie.getId());
        categorieDto.setNom(categorie.getNom());

        return categorieDto;
    };

    private Categorie dtoToEntity(CategorieDto categorieDto){
        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setNom(categorieDto.getNom());

        return categorie;
    };

}
