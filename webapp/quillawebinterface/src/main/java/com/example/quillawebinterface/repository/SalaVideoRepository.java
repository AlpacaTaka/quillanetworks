package com.example.quillawebinterface.repository;


import com.example.quillawebinterface.entity.SalaVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaVideoRepository extends JpaRepository<SalaVideo, Integer> {
}
