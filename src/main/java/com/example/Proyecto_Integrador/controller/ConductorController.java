package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.ConductorDto;
import com.example.Proyecto_Integrador.persistence.entity.Conductor;
import com.example.Proyecto_Integrador.service.ConductorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarConductor(@PathVariable int id){
        if(conductorService.eliminarConductor(id)){
            return ResponseEntity.ok("Se elimino con exito");
        }else {
            return ResponseEntity.internalServerError().body("No se pudo eliminar al conductor");
        }
    }
}
