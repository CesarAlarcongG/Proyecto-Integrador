package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.RutaDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Ruta;
import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.example.Proyecto_Integrador.persistence.repository.AgenciaRepository;
import com.example.Proyecto_Integrador.service.ActividadService;
import com.example.Proyecto_Integrador.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ruta")
public class RutaController {

    @Autowired
    private RutaService rutaService;
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private AgenciaRepository agenciaRepository;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarRuta(@RequestBody RutaDto rutaDto){
        Ruta ruta = rutaService.mapearRuta(rutaDto);

        ruta = rutaService.registrarEnBD(ruta);

        ruta = rutaService.agregarAgencias(ruta, rutaDto.getIdAgencias());

       Actividad actividad = actividadService.obtenerActividad(ruta, ActividadEnum.REGISTRAR, rutaDto.getIdAdministrador());

       if (actividad == null){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se agrego la actividad");
       }

       ruta = rutaService.agregarActividad(actividad, ruta);

       ruta = rutaService.registrarEnBD(ruta);

        return ResponseEntity.ok(ruta);
    }

    @GetMapping("/obtener")
    public ResponseEntity<?> obtenerTodasLasRutas(){
        List<Ruta> rutaList = rutaService.obtenerTodasLasRutas();

        return ResponseEntity.ok(rutaList);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarRuta(@RequestBody RutaDto rutaDto) {
        Optional<Ruta> optionalRuta = rutaService.obtenerPorId(rutaDto.getIdRuta());
        if (optionalRuta.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ruta no encontrada");
        }

        Ruta ruta = optionalRuta.get();

        ruta.setNombreRuta(rutaDto.getNombre());
        // Limpiar agencias actuales de la ruta (para actualizar correctamente)
        rutaService.limpiarRelacionesAgencias(ruta);

        // Establecer nuevas agencias
        ruta = rutaService.agregarAgencias(ruta, rutaDto.getIdAgencias());

        // Registrar en BD después de modificar las relaciones
        ruta = rutaService.registrarEnBD(ruta);

        // Crear y asociar actividad
        Actividad actividad = actividadService.obtenerActividad(ruta, ActividadEnum.ACTUALIZAR, rutaDto.getIdAdministrador());

        if (actividad == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se agregó la actividad");
        }

        ruta = rutaService.agregarActividad(actividad, ruta);

        return ResponseEntity.ok(ruta);
    }





}
