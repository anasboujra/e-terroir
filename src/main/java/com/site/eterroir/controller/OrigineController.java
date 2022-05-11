package com.site.eterroir.controller;

import com.site.eterroir.dto.OrigineDto;
import com.site.eterroir.service.OrigineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity createOrigine(@Valid @RequestBody OrigineDto origineDto) throws URISyntaxException {
        OrigineDto createdDto = origineService.create(origineDto);
        return ResponseEntity.created(new URI("/api/origines/" + createdDto.getId())).body(createdDto);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateOrigine(@PathVariable Long id, @RequestBody OrigineDto origineDto) {
        return ResponseEntity.ok(origineService.update(id, origineDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteOrigine(@PathVariable Long id){
        origineService.delete(id);
        return ResponseEntity.ok().build();
    }
}
