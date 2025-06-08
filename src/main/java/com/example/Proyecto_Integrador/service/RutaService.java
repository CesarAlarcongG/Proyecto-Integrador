package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.RutaDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Ruta;
import com.example.Proyecto_Integrador.persistence.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;


    public Ruta registrarEnBD(Ruta ruta){
        return rutaRepository.save(ruta);
    }

    public Ruta agregarActividad(Actividad actividad, Ruta ruta){
        List<Actividad> actividadList = ruta.getActividades();

        if (actividadList == null) {
            actividadList = new ArrayList<>();
        }

        actividadList.add(actividad);
        ruta.setActividades(actividadList);
        return rutaRepository.save(ruta);
    }
    public Ruta mapearRuta(RutaDto rutaDto){

        return Ruta.builder()
                .nombreRuta(rutaDto.getNombre())
                .agencias(rutaDto.getAgencias())
                .build();
    }

    

}
