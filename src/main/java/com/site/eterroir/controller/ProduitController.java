package com.site.eterroir.controller;

import com.site.eterroir.dto.ProduitDto;
import com.site.eterroir.service.ProduitService;
import com.site.eterroir.specification.ProduitSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public ResponseEntity getProduits(ProduitSpec produitSpec) {
        return ResponseEntity.ok(produitService.list(produitSpec));
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduit(@PathVariable Long id){
        return ResponseEntity.ok(produitService.get(id));
    }

    @PostMapping
    public ResponseEntity createProduit(@Valid @RequestBody ProduitDto produitDto) throws URISyntaxException {
        ProduitDto createdDto = produitService.create(produitDto);
        return ResponseEntity.created(new URI("/api/produits/" + createdDto.getId())).body(createdDto);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateProduit(@PathVariable Long id, @RequestBody ProduitDto produitDto) {
        return ResponseEntity.ok(produitService.update(id, produitDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduit(@PathVariable Long id){
        produitService.delete(id);
        return ResponseEntity.ok().build();
    }

}
