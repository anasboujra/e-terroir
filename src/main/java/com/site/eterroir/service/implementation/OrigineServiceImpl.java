package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Origine;
import com.site.eterroir.repository.OrigineRepo;
import com.site.eterroir.service.OrigineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class OrigineServiceImpl implements OrigineService {

    private final OrigineRepo origineRepo;

    @Override
    public Origine create(Origine origine) {
        return origineRepo.save(origine);
    }

    @Override
    public List<Origine> list() {
        return origineRepo.findAll();
    }

    @Override
    public Origine get(Long id) {
        return origineRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        origineRepo.deleteById(id);
        return true;
    }

    @Override
    public Origine update(Long id, Origine origine) throws Exception {
        if(origineRepo.findById(id).isPresent()) {
            origine.setId(id);
            return origineRepo.save(origine);
        } else{
            throw new Exception("Id n'existe pas");
        }
    }

}
