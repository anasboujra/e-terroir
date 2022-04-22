package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Admin;
import com.site.eterroir.repository.AdminRepo;
import com.site.eterroir.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    private final AdminRepo adminRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Admin create(Admin admin) {
        log.info("saving new admin: " + admin.getEmail());
        admin.setMotDePasse(passwordEncoder.encode(admin.getMotDePasse()));
        return adminRepo.save(admin);
    }

    @Override
    public List<Admin> list() {
        return adminRepo.findAll();
    }

    @Override
    public Admin get(Long id) {
        return adminRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        adminRepo.deleteById(id);
        return true;
    }

    @Override
    public Admin update(Long id, Admin admin) throws Exception {
        if(adminRepo.findById(id).isPresent()) {
            admin.setId(id);
            return adminRepo.save(admin);
        } else{
            throw new Exception("Id n'existe pas");
        }
    }

}
