package com.site.eterroir.repository;

import com.site.eterroir.model.MatierePremiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatierePremiereRepo extends JpaRepository<MatierePremiere, Long> {
    Optional<MatierePremiere> findByNom(String nom);

}
