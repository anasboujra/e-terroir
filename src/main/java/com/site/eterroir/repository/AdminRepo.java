package com.site.eterroir.repository;

import com.site.eterroir.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {
    public Admin findByEmail(String email);
}
