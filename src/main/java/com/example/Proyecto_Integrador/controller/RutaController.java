package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.RutaDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Ruta;
import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.example.Proyecto_Integrador.service.ActividadService;
import com.example.Proyecto_Integrador.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ruta")
public class RutaController {
    @Autowired
    private RutaService rutaService;
    @Autowired
    private ActividadService actividadService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarRuta(@RequestBody RutaDto rutaDto){
        Ruta ruta = rutaService.mapearRuta(rutaDto);

        ruta = rutaService.registrarEnBD(ruta);

       Actividad actividad = actividadService.obtenerActividad(ruta, ActividadEnum.REGISTRAR, rutaDto.getIdAdministrador());

       if (actividad == null){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se agrego la actividad");
       }

       ruta = rutaService.agregarActividad(actividad, ruta);

        return ResponseEntity.ok(ruta);
    }
}
