package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Cooperative;
import com.site.eterroir.model.Cooperative;
import com.site.eterroir.repository.CooperativeRepo;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.CooperativeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Transactional
public class CooperativeServiceImpl implements CooperativeService {

    private final CooperativeRepo cooperativeRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Cooperative create(Cooperative cooperative) {
        if(SecurityService.isAdmin()){
            cooperative.setMotDePasse(passwordEncoder.encode(cooperative.getMotDePasse()));
            return cooperativeRepo.save(cooperative);
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public List<Cooperative> list() {
        return cooperativeRepo.findAll();
    }

    @Override
    public Cooperative get(Long id) {
        return cooperativeRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        if(SecurityService.isAdmin()){
            cooperativeRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public Cooperative update(Long id, Cooperative cooperative){
        Cooperative dbCooperative = cooperativeRepo.findById(id).get();
        if(SecurityService.isAdmin() || isOwner(dbCooperative)) {
            if(cooperative.getEmail() != null)
                dbCooperative.setEmail(cooperative.getEmail());
            if(cooperative.getMotDePasse() != null)
                dbCooperative.setMotDePasse(passwordEncoder.encode(cooperative.getMotDePasse()));
            if(cooperative.getNumTele() != null)
                dbCooperative.setNumTele(cooperative.getNumTele());
            if(cooperative.getNom() != null)
                dbCooperative.setNom(cooperative.getNom());
            if(cooperative.getSecteur() != null)
                dbCooperative.setSecteur(cooperative.getSecteur());
            if(cooperative.getRegion() != null)
                dbCooperative.setRegion(cooperative.getRegion());
            return cooperativeRepo.save(dbCooperative);
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }


    // User check methods:

    private boolean isOwner(Cooperative cooperative) {
        Map<String, String> user = SecurityService.getLoggedUser();
    if( user.get("authority").contains("COOPERATIVE_ROLE")
                &&  user.get("email").equals(cooperative.getEmail()) ) {
            return true;
        }
        return false;
    }

}
