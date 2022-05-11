package com.site.eterroir.service;

import com.site.eterroir.model.Client;

import java.util.List;


public interface ClientService {
    Client create(Client client);
    List<Client> list();
    Client get(Long id);
    Boolean delete(Long id);
    Client update(Long id, Client client);
}
