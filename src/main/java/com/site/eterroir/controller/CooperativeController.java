package com.site.eterroir.controller;

import com.site.eterroir.model.Cooperative;
import com.site.eterroir.service.CooperativeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/cooperatives")
@RequiredArgsConstructor
public class CooperativeController {

    private final CooperativeService cooperativeService;

    @GetMapping
    public ResponseEntity getCooperatives(){
        return ResponseEntity.ok(cooperativeService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCooperative(@PathVariable Long id){
        return ResponseEntity.ok(cooperativeService.get(id));
    }

    @PostMapping
    public ResponseEntity createCooperative(@Valid @RequestBody Cooperative cooperative) throws URISyntaxException {
        Cooperative createdCooperative = cooperativeService.create(cooperative);
        return ResponseEntity.created(new URI("/api/cooperatives/" + createdCooperative.getId())).body(createdCooperative);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateCooperative(@PathVariable Long id, @RequestBody Cooperative cooperative) {
        return ResponseEntity.ok(cooperativeService.update(id, cooperative));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteCooperative(@PathVariable Long id){
        cooperativeService.delete(id);
        return ResponseEntity.ok().build();
    }
}
