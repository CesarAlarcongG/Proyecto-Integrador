package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.AdministradorDto;
import com.example.Proyecto_Integrador.dto.CredencialesDto;
import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import com.example.Proyecto_Integrador.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorService administradorService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarAdministrador(@RequestBody AdministradorDto administradorDto){
        //1. Guardamos en la BD
        Administrador administrador = administradorService.registrarAdministrador(administradorDto);
        if (administrador == null){
            throw new RuntimeException("No se pudo almacenar la información en la BD");

        }
        return ResponseEntity.status(HttpStatus.OK).body(administrador);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdministrador(@RequestBody CredencialesDto credencialesDto){
        //1. Autenticar crednciales
        Authentication authentication = administradorService.autenticarUsuario(credencialesDto);
        if (!authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION).body("El usuario no esta autenticado o registrado en la BD");
        }
        //2. Retornamos la respuesta
        return ResponseEntity.status(HttpStatus.OK).body(administradorService.repuestaLogin(authentication));
    }
}
