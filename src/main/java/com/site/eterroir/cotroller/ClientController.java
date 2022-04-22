package com.site.eterroir.cotroller;

import com.site.eterroir.model.Client;
import com.site.eterroir.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity getClients(){
        return ResponseEntity.ok(clientService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity getClient(@PathVariable Long id){
        return ResponseEntity.ok(clientService.get(id));
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
        Client createdClient = clientService.create(client);
        return ResponseEntity.created(new URI("/api/clients/" + createdClient.getId())).body(createdClient);
    }

    @PutMapping({"{id}"})
    public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client) throws Exception {
        return ResponseEntity.ok(clientService.update(id, client));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        clientService.delete(id);
        return ResponseEntity.ok().build();
    }
}
