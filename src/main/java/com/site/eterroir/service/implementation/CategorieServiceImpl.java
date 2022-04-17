package com.site.eterroir.service.implementation;


import com.site.eterroir.dto.CategorieDto;
import com.site.eterroir.model.Categorie;
import com.site.eterroir.repository.CategorieRepo;
import com.site.eterroir.service.CategorieService;
import lombok.RequiredArgsConstructor;
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
        Categorie categorie = convertToEntity(categorieDto);
        return convertToDto(categorieRepo.save(categorie));
    }

    @Override
    public List<CategorieDto> list() {
        List<Categorie> categories = categorieRepo.findAll();
        List<CategorieDto> dtoList = new ArrayList<>();
        for(Categorie categorie : categories){
            dtoList.add(convertToDto(categorie));
        }
        return dtoList;
    }

    @Override
    public CategorieDto get(Long id) {
        return convertToDto(categorieRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        categorieRepo.deleteById(id);
        return true;
    }

    @Override
    public CategorieDto update(Long id, CategorieDto categorieDto) throws Exception {
        if(categorieRepo.findById(id).isPresent()) {
            Categorie categorie = convertToEntity(categorieDto);
            categorie.setId(id);
            return convertToDto(categorieRepo.save(categorie));
        } else{
            throw new Exception("Id n'existe pas");
        }
    }


    // DTO conversion:

    private CategorieDto convertToDto(Categorie categorie){
        CategorieDto categorieDto = new CategorieDto();
        categorieDto.setId(categorie.getId());
        categorieDto.setNom(categorie.getNom());

        return categorieDto;
    };

    private Categorie convertToEntity(CategorieDto categorieDto){
        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setNom(categorieDto.getNom());

        return categorie;
    };

}
