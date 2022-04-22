package com.site.eterroir.repository;

import com.site.eterroir.model.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CooperativeRepo extends JpaRepository<Cooperative, Long> {
    Cooperative findByEmail(String email);
}
