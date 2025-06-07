package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.AgenciaDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.example.Proyecto_Integrador.service.ActividadService;
import com.example.Proyecto_Integrador.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agencia")
public class AgenciaController {

    @Autowired
    private AgenciaService agenciaService;
    @Autowired
    private ActividadService actividadService;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarAgencia(@RequestBody AgenciaDto agenciaDto){
        //Mapear a clase
        Agencia agenciaMapeado = agenciaService.mapear(agenciaDto);

        // Guardar información en la BD
        Optional<Agencia> agencia = agenciaService.guardarEnLaBD(agenciaMapeado);

        agencia = agenciaService.relacionarActividad(agencia.get(), ActividadEnum.REGISTRAR);

        //Validamos si se gurdo en la BD y devolvemos respuesta en base a ello
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
