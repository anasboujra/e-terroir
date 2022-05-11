package com.site.eterroir.service.implementation;

import com.site.eterroir.dto.ProduitDto;
import com.site.eterroir.model.*;
import com.site.eterroir.repository.*;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.ProduitService;
import com.site.eterroir.specification.ProduitSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepo produitRepo;
    private final CategorieRepo categorieRepo;
    private final CooperativeRepo cooperativeRepo;
    private final MatierePremiereRepo matiereRepo;
    private final OrigineRepo origineRepo;

    @Override
    public ProduitDto create(ProduitDto produitDto) {
        if(SecurityService.isCooperative()) {
            Produit produit = dtoToEntity(produitDto);
            return entityToDto(produitRepo.save(produit));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public List<ProduitDto> list(ProduitSpec produitSpec) {
        List<Produit> produits = produitRepo.findAll(produitSpec);
        List<ProduitDto> dtoList = new ArrayList<>();
        for(Produit produit : produits){
            dtoList.add(entityToDto(produit));
        }
        return dtoList;
    }

    @Override
    public ProduitDto get(Long id) {
        return entityToDto(produitRepo.findById(id).get());
    }

    @Override
    public Boolean delete(Long id) {
        Produit produit = produitRepo.findById(id).get();
        if(isOwner(produit)) {
            produitRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public ProduitDto update(Long id, ProduitDto produitDto) {
        Produit dbProduit = produitRepo.findById(id).get();
        if(isOwner(dbProduit)) { // We didn't use the dtoToEntity function because it has more logic
            if(produitDto.getNom() != null)
                dbProduit.setNom(produitDto.getNom());
            if(produitDto.getDescription() != null)
                dbProduit.setDescription(produitDto.getDescription());
            if(produitDto.getPrix() != null)
                dbProduit.setPrix(produitDto.getPrix());
            if(produitDto.getUnite() != null)
                dbProduit.setUnite(produitDto.getUnite());
            if(produitDto.getQuantite() != null)
                dbProduit.setQuantite(produitDto.getQuantite());
            return entityToDto(produitRepo.save(dbProduit));
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }


    // User check methods:

    private boolean isOwner(Produit produit) {
        Map<String, String> user = SecurityService.getLoggedUser();
        if( user.get("authority").contains("COOPERATIVE_ROLE")
                &&  user.get("email").equals(produit.getCooperative().getEmail()) ) {
            return true;
        }
        return false;
    }


    // DTO conversion:

    private ProduitDto entityToDto(Produit produit){
        ProduitDto produitDto = new ProduitDto();
        produitDto.setId(produit.getId());
        produitDto.setNom(produit.getNom());
        produitDto.setDescription(produit.getDescription());
        produitDto.setPrix(produit.getPrix());
        produitDto.setUnite(produit.getUnite());
        produitDto.setQuantite(produit.getQuantite());

        produitDto.setCategorie(produit.getCategorie().getNom());

        produitDto.setCooperative(produit.getCooperative().getNom());

        List<ProduitMatiereAsso> list = produit.getProduitMatieresAssos();
        for( ProduitMatiereAsso asso : list){
            String matiere = asso.getMatierePremiere().getNom();
            String origine = asso.getOrigine().getNom();
            produitDto.getMatiereOrigine().put(matiere, origine);
        }

        return produitDto;
    };

    private Produit dtoToEntity(ProduitDto produitDto){
        Produit produit = new Produit();
        produit.setId(produitDto.getId());
        produit.setNom(produitDto.getNom());
        produit.setDescription(produitDto.getDescription());
        produit.setPrix(produitDto.getPrix());
        produit.setUnite(produitDto.getUnite());
        produit.setQuantite(produitDto.getQuantite());

        if(categorieRepo.findByNom(produitDto.getCategorie()).isEmpty()) {
            Categorie categorie = new Categorie();
            categorie.setNom(produitDto.getCategorie());
            categorieRepo.save(categorie);
        }
        produit.setCategorie(categorieRepo.findByNom(produitDto.getCategorie()).get());

        String email = SecurityService.getLoggedUser().get("email");
        produit.setCooperative(cooperativeRepo.findByEmail(email));
        produit = produitRepo.save(produit);

        Map<String, String> map = produitDto.getMatiereOrigine();
        for(String matiereKey : map.keySet()){
            if(matiereRepo.findByNom(matiereKey).isEmpty()) {
                MatierePremiere matiere = new MatierePremiere();
                matiere.setNom(matiereKey);
                matiereRepo.save(matiere);
            }
            MatierePremiere matiere = matiereRepo.findByNom(matiereKey).get();

            if(origineRepo.findByNom(map.get(matiereKey)).isEmpty()) {
                Origine origine = new Origine();
                origine.setNom(map.get(matiereKey));
                origineRepo.save(origine);
            }
            Origine origine = origineRepo.findByNom(map.get(matiereKey)).get();

            ProduitMatiereAsso asso = new ProduitMatiereAsso();
            asso.setProduit(produit);
            asso.setMatierePremiere(matiere);
            asso.setOrigine(origine);
            produit.getProduitMatieresAssos().add(asso);
        }
        return produit;
    }

}
