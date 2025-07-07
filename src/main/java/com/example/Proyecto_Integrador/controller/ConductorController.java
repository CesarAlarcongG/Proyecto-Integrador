package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.ConductorDto;
import com.example.Proyecto_Integrador.persistence.entity.Conductor;
import com.example.Proyecto_Integrador.service.ConductorService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conductor")
public class ConductorController {
    @Autowired
    private ConductorService conductorService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarConductor(@RequestBody ConductorDto conductorDTO){
        //Registrar información
        Conductor conductor = conductorService.registrarConductor(conductorDTO);

        //Verificar si el conductor fue registrado
        if (conductor == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No se pudo registra al conductor");
        }

        return ResponseEntity.ok(conductor);
    }
}
