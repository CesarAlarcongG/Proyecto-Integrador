package com.example.Proyecto_Integrador.controller;

import com.example.Proyecto_Integrador.dto.AgenciaDto;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.example.Proyecto_Integrador.service.ActividadService;
import com.example.Proyecto_Integrador.service.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        agencia = agenciaService.relacionarActividad(agencia.get(), ActividadEnum.REGISTRAR, agenciaDto.getIdAdministrador());

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

    @GetMapping("/obtener/todos")
    public ResponseEntity<?> obtenerTodasLasAgencias(){
        List<Agencia> agenciaList = agenciaService.obtenerTodasLasAgencias();

        if (agenciaList.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Aun no ha registrado agencias");
        }

        return ResponseEntity.ok(agenciaList);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarAgencia(@RequestBody AgenciaDto agenciaDto) {
        // Validar que el ID de la agencia esté presente
        if (agenciaDto.getIdAgencia() == null || agenciaDto.getIdAgencia().isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El ID de la agencia es requerido para la actualización.");
        }

        try {
            int id = Integer.parseInt(agenciaDto.getIdAgencia());

            // Buscar la agencia existente
            Optional<Agencia> agenciaExistente = agenciaService.obtenerPorId(id);

            if (!agenciaExistente.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la agencia con ID: " + agenciaDto.getIdAgencia());
            }

            // Actualizar los campos de la agencia existente
            Agencia agenciaActualizar = agenciaExistente.get();
            agenciaActualizar.setDepartamento(agenciaDto.getDepartamento());
            agenciaActualizar.setProvincia(agenciaDto.getProvincia());
            agenciaActualizar.setDirección(agenciaDto.getDirección());
            agenciaActualizar.setReferencia(agenciaDto.getReferencia());

            // Guardar los cambios
            Optional<Agencia> agenciaActualizada = agenciaService.guardarEnLaBD(agenciaActualizar);

            // Registrar la actividad de actualización
            agenciaActualizada = agenciaService.relacionarActividad(
                    agenciaActualizada.get(),
                    ActividadEnum.ACTUALIZAR,
                    agenciaDto.getIdAdministrador()
            );

            return ResponseEntity.ok(agenciaActualizada);

        } catch (NumberFormatException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("El ID de la agencia debe ser un número válido.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar la agencia: " + e.getMessage());
        }
    }



}
