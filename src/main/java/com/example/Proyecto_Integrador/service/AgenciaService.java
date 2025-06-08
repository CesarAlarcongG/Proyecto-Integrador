package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.AgenciaDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.persistence.entity.Ruta;
import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.example.Proyecto_Integrador.persistence.repository.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgenciaService {
    @Autowired
    private AgenciaRepository agenciaRepository;
    @Autowired
    private ActividadService actividadService;

    public Optional<Agencia> guardarEnLaBD(Agencia agencia){
        return Optional.of(agenciaRepository.save(agencia));
    }
    public List<Agencia> obtenerTodasLasAgencias(){
        return agenciaRepository.findAll();
    }
    public Optional<Agencia> obtenerPorId(int id){
        return agenciaRepository.findById(id);
    }



    public Agencia mapear(AgenciaDto agenciaDto){
        return Agencia.builder()
                .departamento(agenciaDto.getDepartamento())
                .provincia(agenciaDto.getProvincia())
                .dirección(agenciaDto.getDirección())
                .actividades(new ArrayList<>())
                .referencia(agenciaDto.getReferencia())
                .build();

    }
    public Optional<Agencia> relacionarActividad(Agencia agencia, ActividadEnum actividadEnum, int id){
        // Almacenamos actividad y lo relacionamos con agencia
        Actividad actividad = actividadService.obtenerActividad(agencia, actividadEnum, id);

        List<Actividad> actividadList = agencia.getActividades();
        actividadList.add(actividad);

        agencia.setActividades(actividadList);

        return Optional.of(agencia);
    }
    public List<Agencia> relacionarAgenciaConRuta(List<Integer> agencias, Ruta ruta) {
        if (agencias == null || agencias.isEmpty()) {
            return new ArrayList<>();
        }
        return agencias.stream()
                .map(idAgencia -> {
                    Agencia agencia = obtenerPorId(idAgencia).get();
                    if (agencia.getRutas() == null) {
                        agencia.setRutas(new ArrayList<>());
                    }
                    agencia.getRutas().add(ruta);
                    agencia = guardarEnLaBD(agencia).get();
                    return agencia;
                })
                .collect(Collectors.toList());
    }




}
