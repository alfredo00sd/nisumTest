package com.example.nisumjava.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<NUser, Long> {

}
