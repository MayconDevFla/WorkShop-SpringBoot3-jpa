package com.teste.demo.config;

import com.teste.demo.repositories.UserRepository;
import com.teste.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Mike", "mike@hotmail.com", "895544123", "449992");
        User u2 = new User(null, "Larissa", "larissa@hotmail.com", "99992222", "5151515");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
