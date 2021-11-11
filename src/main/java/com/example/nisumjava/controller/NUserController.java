package com.example.nisumjava.controller;

import com.example.nisumjava.exceptions.ResourceNotFoundException;
import com.example.nisumjava.model.NUser;
import com.example.nisumjava.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class NUserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<NUser> getAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<NUser> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
        NUser nUser = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(nUser);
    }

    @PostMapping("/users")
    public NUser createUser(@Valid @RequestBody NUser nUser) {
        return repository.save(nUser);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity <NUser> updateUser(@PathVariable(value = "id") Long NUserId,
                                                      @Valid @RequestBody NUser user) throws ResourceNotFoundException {
        NUser nUser = repository.findById(NUserId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + NUserId));

        nUser.setName(user.getName());
        nUser.setEmail(user.getEmail());
        nUser.setPassword(user.getPassword());
        //nUser.setPhones(user.getPhones());

        final NUser updatedUser = repository.save(nUser);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        NUser nUser = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));

        repository.delete(nUser);
        Map <String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
