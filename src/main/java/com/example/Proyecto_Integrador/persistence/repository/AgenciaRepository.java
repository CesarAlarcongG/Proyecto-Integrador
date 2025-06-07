package com.example.Proyecto_Integrador.persistence.repository;

import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, Integer> {

}
