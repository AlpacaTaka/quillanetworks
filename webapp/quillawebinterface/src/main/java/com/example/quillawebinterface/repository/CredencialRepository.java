package com.example.quillawebinterface.repository;

import com.example.quillawebinterface.entity.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, Integer> {
    Optional<Credencial> findByCorreo(String correo);
    Optional<Credencial> findByUsuarioId(Integer usuarioId);
}
