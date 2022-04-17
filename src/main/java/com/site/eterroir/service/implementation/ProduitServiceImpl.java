package com.site.eterroir.service.implementation;

import com.site.eterroir.model.*;
import com.site.eterroir.dto.ProduitDto;
import com.site.eterroir.repository.MatierePremiereRepo;
import com.site.eterroir.repository.OrigineRepo;
import com.site.eterroir.repository.ProduitMatiereAssoRepo;
import com.site.eterroir.repository.ProduitRepo;
import com.site.eterroir.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepo produitRepo;
    private final MatierePremiereRepo matierePremiereRepo;
    private final OrigineRepo origineRepo;
    private final ProduitMatiereAssoRepo produitMatiereAssoRepo;

    @Override
    public ProduitDto create(ProduitDto produitDto) {
        Produit produit = convertToEntity(produitDto);
        return convertToDto(produitRepo.save(produit));
    }

    @Override
    public List<ProduitDto> list() {
        List<Produit> produits = produitRepo.findAll();
        List<ProduitDto> dtoList = new ArrayList<>();
        for(Produit produit : produits){
            dtoList.add(convertToDto(produit));
        }
        return dtoList;
    }

    @Override
    public ProduitDto get(Long id) {
        return convertToDto(produitRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        produitRepo.deleteById(id);
        return true;
    }

    @Override
    public ProduitDto update(Long id, ProduitDto produitDto) throws Exception {
        if(produitRepo.findById(id).isPresent()) {
            Produit produit = convertToEntity(produitDto);
            produit.setId(id);
            return convertToDto(produitRepo.save(produit));
        } else{
            throw new Exception("Id n'existe pas");
        }
    }


    // DTO conversion:

    private ProduitDto convertToDto(Produit produit){
        ProduitDto produitDto = new ProduitDto();
        produitDto.setId(produit.getId());
        produitDto.setNom(produit.getNom());
        produitDto.setDescription(produit.getDescription());
        produitDto.setPrix(produit.getPrix());
        produitDto.setUnite(produit.getUnite());
        produitDto.setQuantite(produit.getQuantite());

        List<ProduitMatiereAsso> list = produit.getProduitMatieresAssos();
        for( ProduitMatiereAsso asso : list){
            Long idMatierePremiere = asso.getMatierePremiere().getId();
            Long idOrigine = asso.getOrigine().getId();
            produitDto.getIdMatiereIdOrigine().put(idMatierePremiere, idOrigine);
        }

        return produitDto;
    };

    private Produit convertToEntity(ProduitDto produitDto){
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setNom(produitDto.getNom());
        produit.setDescription(produitDto.getDescription());
        produit.setPrix(produitDto.getPrix());
        produit.setUnite(produitDto.getUnite());
        produit.setQuantite(produitDto.getQuantite());

        Map<Long, Long> map = produitDto.getIdMatiereIdOrigine();
        for(Long idMatiere : map.keySet()){
            ProduitMatiereKey key = new ProduitMatiereKey();
            key.setIdProduit(produit.getId());
            key.setIdMatierePremiere(idMatiere);
            Optional<ProduitMatiereAsso> assoOptional = produitMatiereAssoRepo.findById(key);
            if(assoOptional.isPresent()){
                produit.getProduitMatieresAssos().add(assoOptional.get());
            } else {
                MatierePremiere matiere = matierePremiereRepo.findById(idMatiere).get();
                Origine origine = origineRepo.findById(map.get(idMatiere)).get();
                ProduitMatiereAsso asso = new ProduitMatiereAsso();
                asso.setProduit(produit);
                asso.setMatierePremiere(matiere);
                asso.setOrigine(origine);
                produit.getProduitMatieresAssos().add(asso);
            }
        }

        return produit;
    };

}
