package com.example.Proyecto_Integrador.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String departamento;
    private String provincia;
    private String dirección;
    private String referencia;

    @OneToMany(mappedBy = "")
    @JsonManagedReference(value = "agencia_actividad")
    private List<Actividad> actividades;

}
