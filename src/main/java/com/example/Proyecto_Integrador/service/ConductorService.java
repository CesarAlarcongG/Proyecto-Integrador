package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.ConductorDto;
import com.example.Proyecto_Integrador.persistence.entity.Conductor;
import com.example.Proyecto_Integrador.persistence.repository.ConductorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConductorService {
    @Autowired
    private ConductorRepository conductorRepository;


    public Conductor registrarConductor(ConductorDto conductorDTO) {
        if ( verificarExistencia(conductorDTO.getDni())){
            throw new RuntimeException("El conductor ya fue registrado");
        }
        //Mapear
        Conductor conductor = mapearDeDtoAConductor(conductorDTO);
        //Guardar
        conductor = conductorRepository.save(conductor);
        return conductor;
    }
    public boolean eliminarConductor(int id) {
        conductorRepository.deleteById(id);
        Optional<Conductor> conductor = conductorRepository.findById(id);

        if (conductor.isPresent()){
            return false;
        }
        return true;
    }

    /// /////////////////////////
    private Boolean verificarExistencia(int dni){
        if (conductorRepository.findByDni(dni) == null){
            return false;
        }else {
            return true;
        }
    }
    private Conductor mapearDeDtoAConductor(ConductorDto conductorDto){
        return Conductor.builder()
                .nombre(conductorDto.getNombre())
                .apellido(conductorDto.getApellido())
                .dni(conductorDto.getDni())
                .numLicenciaConducir(conductorDto.getNumLicenciaConducir())
                .build();
    }


}
