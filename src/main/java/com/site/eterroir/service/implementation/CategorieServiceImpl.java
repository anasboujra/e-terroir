package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Categorie;
import com.site.eterroir.repository.CategorieRepo;
import com.site.eterroir.service.CategorieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepo categorieRepo;

    @Override
    public Categorie create(Categorie categorie) {
        return categorieRepo.save(categorie);
    }

    @Override
    public List<Categorie> list() {
        return categorieRepo.findAll();
    }

    @Override
    public Categorie get(Long id) {
        return categorieRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        categorieRepo.deleteById(id);
        return true;
    }

    @Override
    public Categorie update(Long id, Categorie categorie) throws Exception {
        if(categorieRepo.findById(id).isPresent()) {
            categorie.setId(id);
            return categorieRepo.save(categorie);
        } else{
            throw new Exception("Id n'existe pas");
        }
    }

}
