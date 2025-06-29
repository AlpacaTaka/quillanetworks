package com.example.quillawebinterface.repository;

import com.example.quillawebinterface.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByEliminadoFalse();
    List<Usuario> findByEstadoTrueAndEliminadoFalse();
    Optional<Usuario> findByIdAndEliminadoFalse(Integer id);
    List<Usuario> findByRolNombreAndEliminadoFalse(String rolNombre);

    @Query("SELECT u FROM Usuario u JOIN u.credencial c WHERE c.correo = :correo AND u.eliminado = false")
    Optional<Usuario> findByCorreo(@Param("correo") String correo);
}