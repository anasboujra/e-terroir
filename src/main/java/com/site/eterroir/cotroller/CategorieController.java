package com.site.eterroir.cotroller;

import com.site.eterroir.dto.CategorieDto;
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
    public ResponseEntity createCategorie(@RequestBody CategorieDto categorieDto) throws URISyntaxException {
        CategorieDto createdCategorieDto = categorieService.create(categorieDto);
        return ResponseEntity.created(new URI("/api/categories/" + createdCategorieDto.getId())).body(createdCategorieDto);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateCategorie(@PathVariable Long id, @RequestBody CategorieDto categorieDto) throws Exception {
        return ResponseEntity.ok(categorieService.update(id, categorieDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCategorie(@PathVariable Long id){
        categorieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
