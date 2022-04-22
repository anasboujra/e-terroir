package com.site.eterroir.repository;

import com.site.eterroir.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
}
