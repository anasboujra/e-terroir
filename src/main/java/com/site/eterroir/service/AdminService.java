package com.site.eterroir.service;

import com.site.eterroir.model.Admin;

import java.util.List;


public interface AdminService {
    Admin create(Admin admin);
    List<Admin> list();
    Admin get(Long id);
    Boolean delete(Long id);
    Admin update(Long id, Admin admin) throws Exception;
}
