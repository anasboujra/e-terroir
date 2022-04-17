package com.site.eterroir.repository;

import com.site.eterroir.model.ProduitMatiereAsso;
import com.site.eterroir.model.ProduitMatiereKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitMatiereAssoRepo extends JpaRepository<ProduitMatiereAsso, ProduitMatiereKey> {
}
