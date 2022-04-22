package com.site.eterroir.cotroller;

import com.site.eterroir.model.Categorie;
import com.site.eterroir.service.CategorieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.ok(categorieService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategorie(@PathVariable Long id){
        return ResponseEntity.ok(categorieService.get(id));
    }

    @PostMapping
    public ResponseEntity createCategorie(@RequestBody Categorie categorie) throws URISyntaxException {
        return ResponseEntity.created(new URI("/api/categories/" + categorie.getId())).body(categorie);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateCategorie(@PathVariable Long id, @RequestBody Categorie categorie) throws Exception {
        return ResponseEntity.ok(categorieService.update(id, categorie));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategorie(@PathVariable Long id){
        categorieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
