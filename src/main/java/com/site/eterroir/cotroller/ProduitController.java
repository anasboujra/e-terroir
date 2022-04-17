package com.site.eterroir.cotroller;

import com.site.eterroir.dto.ProduitDto;
import com.site.eterroir.service.ProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;


@RestController
@RequestMapping("/api/produits")
@RequiredArgsConstructor
public class ProduitController {

    private final ProduitService produitService;

    @GetMapping
    public ResponseEntity getProduits(){
        return ResponseEntity.ok(produitService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduit(@PathVariable Long id){
        return ResponseEntity.ok(produitService.get(id));
    }

    @PostMapping
    public ResponseEntity createProduit(@RequestBody ProduitDto produitDto) throws URISyntaxException {
        ProduitDto createdProduitDto = produitService.create(produitDto);
        return ResponseEntity.created(new URI("/api/produits/" + createdProduitDto.getId())).body(createdProduitDto);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateProduit(@PathVariable Long id, @RequestBody ProduitDto produitDto) throws Exception {
        return ResponseEntity.ok(produitService.update(id, produitDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduit(@PathVariable Long id){
        produitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
