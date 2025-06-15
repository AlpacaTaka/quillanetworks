package com.example.quillawebinterface.repository;

import com.example.quillawebinterface.entity.RegistroLlamadas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroLlamadasRepository extends JpaRepository<RegistroLlamadas, Integer> {
}
