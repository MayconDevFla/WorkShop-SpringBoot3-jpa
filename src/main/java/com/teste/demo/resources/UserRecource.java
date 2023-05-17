package com.teste.demo.resources;

import com.teste.demo.entities.User;
import com.teste.demo.services.UserService;
import jakarta.annotation.Resource;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserRecource {

    @Autowired
    private UserService service;
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    // PARA DIZER QUE O OBJETO USER VAI CHEGAR NO MODO JSON NA HORA DE FAZER UMA REQUISIÇÃO
    // PARA ESSE JSON SER CONVERTIDO PARA OBJETO USER, TEMOS QUE COLOCAR ANOTATION @RequestBody

    //UTILIZAMOS URI PARA RETORNAR O ID QUE FOI INSERIDO NO BANCO DE DADOS
    // PORQUE PARA RETORNAR UM CREATED NO ResponseEntity ELE PEDE UM URI
    // ESSE URI CONTÉM O ENDEREÇO DO NOVO RECURSO QUE INSERIMOS
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

}
