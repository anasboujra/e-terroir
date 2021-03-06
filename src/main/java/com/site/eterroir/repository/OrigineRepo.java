package com.site.eterroir.repository;

import com.site.eterroir.model.Origine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrigineRepo extends JpaRepository<Origine, Long> {
    Optional<Origine> findByNom(String nom);
}
