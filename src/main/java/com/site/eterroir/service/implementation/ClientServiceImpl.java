package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Client;
import com.site.eterroir.repository.ClientRepo;
import com.site.eterroir.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Client create(Client client) {
        log.info("saving new client: " + client.getEmail());
        client.setMotDePasse(passwordEncoder.encode(client.getMotDePasse()));
        return clientRepo.save(client);
    }

    @Override
    public List<Client> list() {
        return clientRepo.findAll();
    }

    @Override
    public Client get(Long id) {
        return clientRepo.findById(id).get();
    }

    @Override
    public Boolean delete(Long id) {
        clientRepo.deleteById(id);
        return true;
    }

    @Override
    public Client update(Long id, Client client) throws Exception {
        if(clientRepo.findById(id).isPresent()) {
            client.setId(id);
            return clientRepo.save(client);
        } else{
            throw new Exception("Id n'existe pas");
        }
    }

}
