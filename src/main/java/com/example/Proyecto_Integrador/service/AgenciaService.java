package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.AgenciaDto;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.persistence.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;

    public Agencia mapear(AgenciaDto agenciaDto){
        return Agencia.builder()
                .departamento(agenciaDto.getDepartamento())
                .provincia(agenciaDto.getProvincia())
                .dirección(agenciaDto.getDirección())
                .referencia(agenciaDto.getReferencia())
                .build();

    }

    public Optional<Agencia> guardarEnLaBD(Agencia agencia){
        return Optional.of(agenciaRepository.save(agencia));
    }
}
