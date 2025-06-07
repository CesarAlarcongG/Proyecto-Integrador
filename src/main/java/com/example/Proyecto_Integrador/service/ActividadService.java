package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.example.Proyecto_Integrador.persistence.repository.ActividadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ActividadService {

    @Autowired
    private ActividadRepository actividadRepository;
    @Autowired
    private AdministradorService administradorService;

    public Actividad obtenerActividad(Object object, ActividadEnum actividadEnum, int id){
        Actividad actividad = Actividad.builder()
                .actividadEnum(actividadEnum)
                .fecha(new Date())
                .build();
        Administrador administrador = administradorService.obtenerPorId(id);
        actividad.setAdministrador(administrador);

        if(object instanceof Agencia agencia){
            actividad.setAgencia(agencia);
        }
        actividad = actividadRepository.save(actividad);
        administradorService.agregarActividad(administrador, actividad);

        return actividad;

    }


}
