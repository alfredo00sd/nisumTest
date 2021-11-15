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

//    @GetMapping("/users/{id}")
//    public ResponseEntity<NUser> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
//        NUser nUser = nUserService.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
//        return ResponseEntity.ok().body(nUser);
//    }

    @PostMapping("/users")
    public NUser createUser(@RequestBody NUser user) {

        //Input validation.
//        if(validUserMail(user.getEmail())) {
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

            return repository.save(nUser);

//        }//El correo ya esta registrado

//        return null;
    }

    private boolean validUserMail(String email) {

        Pattern pattern = Pattern.compile("^(.+)@(.+)$");
        Matcher matcher = pattern.matcher(email);

        if(!matcher.matches())
            return false;

        NUser nUser = repository.findByEmail(email);

        return nUser != null;
    }

    private boolean validPassword(String pass) {
        Pattern pattern = Pattern.compile("^(.+)@(.+)$");

        Matcher matcher = pattern.matcher(pass);
        return true;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public NUser getNUserPhones(@PathVariable int userId) {
        return repository.findById(userId);
    }

//
//    @PutMapping("/users/{id}")
//    public ResponseEntity <NUser> updateUser(@PathVariable(value = "id") Long NUserId,
//                                                      @Valid @RequestBody NUser user) throws ResourceNotFoundException {
//        NUser nUser = nUserService.findById(NUserId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + NUserId));
//
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//
//        nUser.setName(user.getName());
//        nUser.setEmail(user.getEmail());
//        nUser.setPassword(user.getPassword());
//        nUser.setPhones(null);
//        nUser.setCreated(dateTimeFormatter.format(now));
//        nUser.setLastLogin(dateTimeFormatter.format(now));
//        nUser.setModified(null);
//        nUser.setActive(true);
//        nUser.setToken("token");
//
//        final NUser updatedUser = nUserService.save(nUser);
//
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
//            throws ResourceNotFoundException {
//        NUser nUser = nUserService.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
//
//        nUserService.delete(nUser);
//        Map <String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return response;
//    }

}
