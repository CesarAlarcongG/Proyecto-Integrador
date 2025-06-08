package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.RutaDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import com.example.Proyecto_Integrador.persistence.entity.Ruta;
import com.example.Proyecto_Integrador.persistence.repository.RutaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RutaService {

    @Autowired
    private RutaRepository rutaRepository;
    @Autowired
    private AgenciaService agenciaService;


    public Ruta registrarEnBD(Ruta ruta){
        return rutaRepository.save(ruta);
    }

    public List<Ruta> obtenerTodasLasRutas(){
        return rutaRepository.findAll();
    }

    public Ruta agregarAgencias(Ruta ruta, List<Integer> agencias){
        List<Agencia> agenciaList = agenciaService.relacionarAgenciaConRuta(agencias, ruta );
        ruta.setAgencias(agenciaList);
        return ruta;
    }
    // Agregar este método en RutaService
    public Optional<Ruta> obtenerPorId(int id) {
        return rutaRepository.findById(id);
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
                .agencias(new ArrayList<>())
                .build();
    }

    // Método auxiliar para limpiar relaciones existentes
    public void limpiarRelacionesAgencias(Ruta ruta) {
        // Obtener copia de las agencias actuales para evitar ConcurrentModificationException
        List<Agencia> agenciasActuales = new ArrayList<>(ruta.getAgencias());

        for (Agencia agencia : agenciasActuales) {
            agencia.getRutas().remove(ruta);
            agenciaService.guardarEnLaBD(agencia);
        }

        ruta.getAgencias().clear();
        rutaRepository.save(ruta);
    }

}
