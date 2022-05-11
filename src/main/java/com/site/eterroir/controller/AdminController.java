package com.site.eterroir.controller;

import com.site.eterroir.model.Admin;
import com.site.eterroir.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity getAdmins(){
        return ResponseEntity.ok(adminService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdmin(@PathVariable Long id){
        return ResponseEntity.ok(adminService.get(id));
    }

    @PostMapping
    public ResponseEntity createAdmin(@Valid @RequestBody Admin admin) throws URISyntaxException {
        Admin createdAdmin = adminService.create(admin);
        return ResponseEntity.created(new URI("/api/admins/" + createdAdmin.getId())).body(createdAdmin);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return ResponseEntity.ok(adminService.update(id, admin));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAdmin(@PathVariable Long id){
        adminService.delete(id);
        return ResponseEntity.ok().build();
    }
}
