package com.example.Proyecto_Integrador.persistence.repository;

import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {
    Optional<Administrador> findByCorreo(String correo);
}
