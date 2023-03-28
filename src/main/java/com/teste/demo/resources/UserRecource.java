package com.teste.demo.resources;

import com.teste.demo.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserRecource {

    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1L, "Michael", "michael@hotmail.com", "454888", "7878484");
        return ResponseEntity.ok().body(u);
    }
}
