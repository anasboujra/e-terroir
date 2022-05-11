package com.site.eterroir.repository;

import com.site.eterroir.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategorieRepo extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByNom(String nom);
}
