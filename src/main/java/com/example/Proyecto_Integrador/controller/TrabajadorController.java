package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.TrabajadorDto;
import com.example.Proyecto_Integrador.persistence.entity.Trabajador;
import com.example.Proyecto_Integrador.service.TrabajadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trabajador")
public class TrabajadorController {

    @Autowired
    private TrabajadorService trabajadorService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarTrabjador(@RequestBody TrabajadorDto trabajadorDto){
        //1. Verificamos si el trabajador ya existe en la BD
        if(trabajadorService.findByDni(trabajadorDto.getDni()) != null){
            return ResponseEntity.status(409).body("Ya hay un ususario");
        }

        //2. Registramos el usuario
        Trabajador trabajador = trabajadorService.guardarEnLaBD(trabajadorDto);

        //3. Retornamos valor
        return ResponseEntity.ok(trabajador);
    }
}
