package com.site.eterroir.service.implementation;

import com.site.eterroir.dto.MatierePremiereDto;
import com.site.eterroir.model.MatierePremiere;
import com.site.eterroir.repository.MatierePremiereRepo;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.MatierePremiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class MatierePremiereServiceImpl implements MatierePremiereService {

    private final MatierePremiereRepo matiereRepo;

    @Override
    public MatierePremiereDto create(MatierePremiereDto matiereDto) {
        if(SecurityService.isAdmin()) {
            MatierePremiere matiere = dtoToEntity(matiereDto);
            return entityToDto(matiereRepo.save(matiere));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public List<MatierePremiereDto> list() {
        List<MatierePremiere> matieres = matiereRepo.findAll();
        List<MatierePremiereDto> dtoList = new ArrayList<>();
        for(MatierePremiere matiere : matieres){
            dtoList.add(entityToDto(matiere));
        }
        return dtoList;
    }

    @Override
    public MatierePremiereDto get(Long id) {
        return entityToDto(matiereRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        if(SecurityService.isAdmin()) {
            matiereRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public MatierePremiereDto update(Long id, MatierePremiereDto matiereDto) {
        if(SecurityService.isAdmin()) {
            MatierePremiere dbMatierePremiere = matiereRepo.findById(id).get();
            MatierePremiere matiere = dtoToEntity(matiereDto);
            if(matiere.getNom() != null)
                dbMatierePremiere.setNom(matiere.getNom());
            return entityToDto(matiereRepo.save(dbMatierePremiere));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }


    // DTO conversion:

    private MatierePremiereDto entityToDto(MatierePremiere matiere){
        MatierePremiereDto matiereDto = new MatierePremiereDto();
        matiereDto.setId(matiere.getId());
        matiereDto.setNom(matiere.getNom());

        return matiereDto;
    };

    private MatierePremiere dtoToEntity(MatierePremiereDto matiereDto){
        MatierePremiere matiere = new MatierePremiere();
        matiere.setId(matiereDto.getId());
        matiere.setNom(matiereDto.getNom());

        return matiere;
    };

}
