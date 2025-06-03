package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.AdministradorDto;
import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import com.example.Proyecto_Integrador.persistence.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Administrador registrarAdministrador(AdministradorDto administradorDto){

        //1. Mapear de DTO a Clase
        Administrador administrador = mapearAdministrador(administradorDto);

        //2. Guardar en la BD
        return administradorRepository.save(administrador);
    }

    /// ////////////////////////////////////////////////////////////////////////////////
    public Administrador mapearAdministrador(AdministradorDto administradorDto){
        return Administrador.builder()
                .nombre(administradorDto.getNombre())
                .apellido(administradorDto.getApellido())
                .correo(administradorDto.getCorreo())
                .contraseña(passwordEncoder.encode(administradorDto.getContraseña()))
                .dni(administradorDto.getDni())
                .numeroLicenciaDeConducir(administradorDto.getNumeroLicenciaDeConducir())
                .build();
    }
}
