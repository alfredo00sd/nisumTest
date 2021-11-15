package com.example.nisumjava.repository;

import com.example.nisumjava.model.NUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NUserRepository extends JpaRepository<NUser, String> {
    NUser findByEmail(String email);
}
