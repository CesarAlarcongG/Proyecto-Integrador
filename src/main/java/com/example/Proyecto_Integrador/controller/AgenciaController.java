package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.AgenciaDto;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAgencia(@RequestBody AgenciaDto agenciaDto){
        //1. Mapear a clase
        Agencia agenciaMapeado = agenciaService.mapear(agenciaDto);
        //2. Guardar información en la BD
        Optional<Agencia> agencia = agenciaService.guardarEnLaBD(agenciaMapeado);

        //3. Validamos si se gurdo en la BD y devolvemos respuesta en base a ello
        if (agencia.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(agencia);
        }else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("No se pudo registrar la agencia.");
        }

    }
}
