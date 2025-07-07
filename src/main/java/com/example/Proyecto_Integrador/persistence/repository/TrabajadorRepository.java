package com.example.Proyecto_Integrador.persistence.repository;

import com.example.Proyecto_Integrador.persistence.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    Trabajador findByDni(int dni);

    Trabajador findById(int id);
}
