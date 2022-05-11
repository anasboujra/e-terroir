package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Admin;
import com.site.eterroir.repository.AdminRepo;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin create(Admin admin) {
        if(SecurityService.isAdmin()){
            admin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
            return adminRepo.save(admin);
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public List<Admin> list() {
        if(SecurityService.isAdmin()){
            return adminRepo.findAll();
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public Admin get(Long id) {
        if(SecurityService.isAdmin()){
            return adminRepo.findById(id).get();
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public boolean delete(Long id) {
        if(SecurityService.isAdmin()){
            adminRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public Admin update(Long id, Admin admin) {
        if(SecurityService.isAdmin()){
            Admin dbAdmin = adminRepo.findById(id).get();
            if(admin.getEmail() != null)
                dbAdmin.setEmail(admin.getEmail());
            if(admin.getMotDePasse() != null)
                dbAdmin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
            if(admin.getNumTele() != null)
                dbAdmin.setNumTele(admin.getNumTele());
            if(admin.getNom() != null)
                dbAdmin.setNom(admin.getNom());
            if(admin.getPrenom() != null)
                dbAdmin.setPrenom(admin.getPrenom());
            if(admin.getCin() != null)
                dbAdmin.setCin(admin.getCin());
            if(admin.getDateNaissance() != null)
                dbAdmin.setDateNaissance(admin.getDateNaissance());
            return adminRepo.save(dbAdmin);
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }
}
