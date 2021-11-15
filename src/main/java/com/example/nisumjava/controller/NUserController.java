package com.example.nisumjava.controller;

import com.example.nisumjava.model.NUser;
import com.example.nisumjava.repository.NUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/")
public class NUserController {

    @Autowired
    private NUserRepository repository;

    @GetMapping("/users")
    public List<NUser> getAllUsers() {
        return repository.findAll();
    }

    @PostMapping("/users")
    public ResponseEntity<NUser> createUser(@RequestBody NUser user) throws Exception {

        //Input validation.
        if(!validUserMail(user.getEmail())) {
            throw new Exception("Correo "+user.getEmail()+" invalido, favor revisar e intentar de nuevo.");
        }

        if(repository.findByEmail(user.getEmail()) != null) {
            throw new Exception("El correo ya esta registrado");
        }

        if(validPassword(user.getPassword())) {
            throw new Exception("Your password must contain at least 1 capital letter and 2 numbers");
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        NUser nUser = new NUser();

        nUser.setUserId(UUID.randomUUID());

        nUser.setName(user.getName());
        nUser.setEmail(user.getEmail());
        nUser.setPassword(user.getPassword());
        nUser.setPhones(user.getPhones());

        nUser.setCreated(dateTimeFormatter.format(now));
        nUser.setLastLogin(dateTimeFormatter.format(now));
        nUser.setModified(null);
        nUser.setToken(UUID.randomUUID());
        nUser.setActive(true);

        return ResponseEntity.ok(repository.save(nUser));
    }

    private boolean validUserMail(String email) {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean validPassword(String pass) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        Matcher matcher = pattern.matcher(pass);
        return true;
    }
}
