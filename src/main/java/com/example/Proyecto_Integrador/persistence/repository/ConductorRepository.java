package com.example.Proyecto_Integrador.persistence.repository;

import com.example.Proyecto_Integrador.persistence.entity.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Integer> {
    Conductor findByDni(int dni);
}
