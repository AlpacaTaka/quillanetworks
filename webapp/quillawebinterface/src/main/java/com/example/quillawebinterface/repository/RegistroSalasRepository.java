package com.example.quillawebinterface.repository;


import com.example.quillawebinterface.entity.RegistroSalas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroSalasRepository extends JpaRepository<RegistroSalas, Integer> {
}
