package com.site.eterroir.controller;

import com.site.eterroir.dto.MatierePremiereDto;
import com.site.eterroir.service.MatierePremiereService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/matieres")
@RequiredArgsConstructor
public class MatierePremiereController {

    private final MatierePremiereService matiereService;

    @GetMapping
    public ResponseEntity getMatierePremieres(){
        return ResponseEntity.ok(matiereService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getMatierePremiere(@PathVariable Long id){
        return ResponseEntity.ok(matiereService.get(id));
    }

    @PostMapping
    public ResponseEntity createMatierePremiere(@Valid @RequestBody MatierePremiereDto matiereDto) throws URISyntaxException {
        MatierePremiereDto createdDto = matiereService.create(matiereDto);
        return ResponseEntity.created(new URI("/api/matieres/" + createdDto.getId())).body(createdDto);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateMatierePremiere(@PathVariable Long id, @RequestBody MatierePremiereDto matiereDto) {
        return ResponseEntity.ok(matiereService.update(id, matiereDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteMatierePremiere(@PathVariable Long id){
        matiereService.delete(id);
        return ResponseEntity.ok().build();
    }
}
