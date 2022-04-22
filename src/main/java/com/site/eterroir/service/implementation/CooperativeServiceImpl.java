package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Cooperative;
import com.site.eterroir.repository.CooperativeRepo;
import com.site.eterroir.service.CooperativeService;
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
public class CooperativeServiceImpl implements CooperativeService {

    private final CooperativeRepo cooperativeRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Cooperative create(Cooperative cooperative) {
        log.info("saving new cooperative: " + cooperative.getEmail());
        cooperative.setMotDePasse(passwordEncoder.encode(cooperative.getMotDePasse()));
        return cooperativeRepo.save(cooperative);
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
        cooperativeRepo.deleteById(id);
        return true;
    }

    @Override
    public Cooperative update(Long id, Cooperative cooperative) throws Exception {
        if(cooperativeRepo.findById(id).isPresent()) {
            cooperative.setId(id);
            return cooperativeRepo.save(cooperative);
        } else{
            throw new Exception("Id n'existe pas");
        }
    }

}
