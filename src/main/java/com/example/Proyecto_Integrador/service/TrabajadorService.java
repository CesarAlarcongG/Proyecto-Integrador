package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.TrabajadorDto;
import com.example.Proyecto_Integrador.persistence.entity.Trabajador;
import com.example.Proyecto_Integrador.persistence.entity.enums.Cargo;
import com.example.Proyecto_Integrador.persistence.repository.TrabajadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;


    public Trabajador findByDni(int dni) {
        return trabajadorRepository.findByDni(dni);
    }

    public Trabajador guardarEnLaBD(TrabajadorDto trabajadorDto) {
        //Mapear Trabjador
        Trabajador trabajador = mapearDeDtoATrabajador(trabajadorDto);
        //Almacenar en la BD
        trabajador = trabajadorRepository.save(trabajador);

        if (trabajador == null){
            throw new RuntimeException("Error al almacenar el trabajador en el BD");
        }
        return trabajador;
    }

    public Trabajador actualizarDatos(TrabajadorDto trabajadorDto) {
        // 1. Buscar el trabajador existente
        Trabajador trabajadorExistente = trabajadorRepository.findById(trabajadorDto.getId());

        // 2. Actualizar sus datos
        trabajadorExistente = actualizarDatos(trabajadorExistente, trabajadorDto);

        // 3. Guardar los cambios
        return trabajadorRepository.save(trabajadorExistente);
    }

    public Trabajador eliminarPorId(int id) {
        trabajadorRepository.deleteById(id);
        return trabajadorRepository.findById(id);
    }

    /// //////////////////////////////////////////////
    private Trabajador mapearDeDtoATrabajador(TrabajadorDto trabajadorDto){
        return Trabajador.builder()
                .nombre(trabajadorDto.getNombre())
                .apellido(trabajadorDto.getApellido())
                .dni(trabajadorDto.getDni())
                .cargo(Cargo.valueOf(trabajadorDto.getCargo().toUpperCase()))
                .build();
    }

    private Trabajador actualizarDatos(Trabajador trabajadorExistente, TrabajadorDto trabajadorDto){
        trabajadorExistente.setNombre(trabajadorDto.getNombre());
        trabajadorExistente.setApellido(trabajadorDto.getApellido());
        trabajadorExistente.setDni(trabajadorDto.getDni());
        trabajadorExistente.setCargo(Cargo.valueOf(trabajadorDto.getCargo().toUpperCase()));
        return trabajadorExistente;
    }



}
