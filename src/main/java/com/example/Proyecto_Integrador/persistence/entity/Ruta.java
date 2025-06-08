package com.example.Proyecto_Integrador.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreRuta;

    @OneToMany(mappedBy = "ruta")
    @JsonManagedReference(value = "ruta_agencia")
    private List<Agencia> agencias = new ArrayList<>();

    @OneToMany(mappedBy = "ruta")
    @JsonManagedReference(value = "ruta_actividad")
    private List<Actividad> actividades = new ArrayList<>();
}
