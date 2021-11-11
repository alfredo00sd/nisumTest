package com.example.nisumjava.repository;

import com.example.nisumjava.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, String> {

}
