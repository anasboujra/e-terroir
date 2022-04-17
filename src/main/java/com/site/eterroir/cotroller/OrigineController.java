package com.site.eterroir.cotroller;


import com.site.eterroir.model.Origine;
import com.site.eterroir.service.OrigineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/origines")
@RequiredArgsConstructor
public class OrigineController {

    private final OrigineService origineService;

    @GetMapping
    public ResponseEntity getOrigines(){
        return ResponseEntity.ok(origineService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getOrigine(@PathVariable Long id){
        return ResponseEntity.ok(origineService.get(id));
    }

    @PostMapping
    public ResponseEntity createOrigine(@RequestBody Origine origine) throws URISyntaxException {
        Origine createdOrigine = origineService.create(origine);
        return ResponseEntity.created(new URI("/api/origines/" + createdOrigine.getId())).body(createdOrigine);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateOrigine(@PathVariable Long id, @RequestBody Origine origine) throws Exception {
        return ResponseEntity.ok(origineService.update(id, origine));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteOrigine(@PathVariable Long id){
        origineService.delete(id);
        return ResponseEntity.ok().build();
    }
}