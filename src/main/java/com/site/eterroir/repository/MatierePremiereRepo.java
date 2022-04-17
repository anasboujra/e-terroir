package com.site.eterroir.repository;

import com.site.eterroir.model.MatierePremiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatierePremiereRepo extends JpaRepository<MatierePremiere, Long> {
    public MatierePremiere findByNom(String nom);
}
