package com.example.Proyecto_Integrador.persistence.repository;

import com.example.Proyecto_Integrador.persistence.entity.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Integer> {
}
