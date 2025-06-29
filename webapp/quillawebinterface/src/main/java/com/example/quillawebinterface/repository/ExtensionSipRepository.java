package com.example.quillawebinterface.repository;

import com.example.quillawebinterface.entity.ExtensionSip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExtensionSipRepository extends JpaRepository<ExtensionSip, Integer> {
    Optional<ExtensionSip> findByNumeroExt(String numeroExt);
    Optional<ExtensionSip> findByUsuarioId(Integer usuarioId);
    List<ExtensionSip> findByUsuarioEliminadoFalse();
}
