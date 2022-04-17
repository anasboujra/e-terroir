package com.site.eterroir.service.implementation;

import com.site.eterroir.dto.MatierePremiereDto;
import com.site.eterroir.model.MatierePremiere;
import com.site.eterroir.repository.MatierePremiereRepo;
import com.site.eterroir.service.MatierePremiereService;
import lombok.RequiredArgsConstructor;
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
        MatierePremiere matiere = convertToEntity(matiereDto);
        return convertToDto(matiereRepo.save(matiere));
    }

    @Override
    public List<MatierePremiereDto> list() {
        List<MatierePremiere> matieres = matiereRepo.findAll();
        List<MatierePremiereDto> dtoList = new ArrayList<>();
        for(MatierePremiere matiere : matieres){
            dtoList.add(convertToDto(matiere));
        }
        return dtoList;
    }

    @Override
    public MatierePremiereDto get(Long id) {
        return convertToDto(matiereRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        matiereRepo.deleteById(id);
        return true;
    }

    @Override
    public MatierePremiereDto update(Long id, MatierePremiereDto matiereDto) throws Exception {
        if(matiereRepo.findById(id).isPresent()) {
            MatierePremiere matiere = convertToEntity(matiereDto);
            matiere.setId(id);
            return convertToDto(matiereRepo.save(matiere));
        } else{
            throw new Exception("Id n'existe pas");
        }
    }


    // DTO conversion:

    private MatierePremiereDto convertToDto(MatierePremiere matiere){
        MatierePremiereDto matiereDto = new MatierePremiereDto();
        matiereDto.setId(matiere.getId());
        matiereDto.setNom(matiere.getNom());
        matiereDto.setDescription(matiere.getDescription());

        return matiereDto;
    };

    private MatierePremiere convertToEntity(MatierePremiereDto matiereDto){
        MatierePremiere matiere = new MatierePremiere();
        matiere.setId(matiereDto.getId());
        matiere.setNom(matiereDto.getNom());
        matiere.setDescription(matiereDto.getDescription());

        return matiere;
    };


}
