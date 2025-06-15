package com.example.quillawebinterface.repository;


import com.example.quillawebinterface.entity.ExtensionSip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtensionSipRepository extends JpaRepository<ExtensionSip, Integer> {
}
