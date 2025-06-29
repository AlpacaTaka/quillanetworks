package com.example.quillawebinterface.repository;

import com.example.quillawebinterface.entity.SalaVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SalaVideoRepository extends JpaRepository<SalaVideo, Integer> {
    List<SalaVideo> findByUsuarioIdAndUsuarioEliminadoFalse(Integer usuarioId);
    List<SalaVideo> findByUsuarioEliminadoFalse();
    Optional<SalaVideo> findByIdAndUsuarioEliminadoFalse(Integer id);
}
