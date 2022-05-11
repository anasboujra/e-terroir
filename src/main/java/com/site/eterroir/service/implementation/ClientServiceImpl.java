package com.site.eterroir.service.implementation;

import com.site.eterroir.model.Client;
import com.site.eterroir.repository.ClientRepo;
import com.site.eterroir.security.SecurityService;
import com.site.eterroir.service.ClientService;
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
public class ClientServiceImpl implements ClientService {

    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Client create(Client client) {
        client.setMotDePasse(passwordEncoder.encode(client.getMotDePasse()));
        return clientRepo.save(client);
    }

    @Override
    public List<Client> list() {
        if(SecurityService.isAdmin()){
            return clientRepo.findAll();
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public Client get(Long id) {
        Client client = clientRepo.findById(id).get();
        if(SecurityService.isAdmin() || isOwner(client)) {
            return client;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public Boolean delete(Long id) {
        if(SecurityService.isAdmin()){
            clientRepo.deleteById(id);
            return true;
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }

    @Override
    public Client update(Long id, Client client){
        Client dbClient = clientRepo.findById(id).get();
        if(SecurityService.isAdmin() || isOwner(dbClient)) {
            if(client.getEmail() != null)
                dbClient.setEmail(client.getEmail());
            if(client.getMotDePasse() != null)
                dbClient.setMotDePasse(passwordEncoder.encode(client.getMotDePasse()));
            if(client.getNumTele() != null)
                dbClient.setNumTele(client.getNumTele());
            if(client.getNom() != null)
                dbClient.setNom(client.getNom());
            if(client.getPrenom() != null)
                dbClient.setPrenom(client.getPrenom());
            if(client.getCin() != null)
                dbClient.setCin(client.getCin());
            if(client.getDateNaissance() != null)
                dbClient.setDateNaissance(client.getDateNaissance());
            if(client.getAdresse() != null)
                dbClient.setAdresse(client.getAdresse());
            return clientRepo.save(dbClient);
        } else {
            throw new AccessDeniedException("Vous n'avez pas l'autorisation");
        }
    }


    // User check methods:

    private boolean isOwner(Client client) {
        Map<String, String> user = SecurityService.getLoggedUser();
        if( user.get("authority").contains("CLIENT_ROLE")
                &&  user.get("email").equals(client.getEmail()) ) {
            return true;
        }
        return false;
    }


}
