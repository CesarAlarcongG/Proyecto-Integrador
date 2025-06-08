package com.example.Proyecto_Integrador.persistence.entity;

import com.example.Proyecto_Integrador.persistence.entity.enums.ActividadEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private ActividadEnum actividadEnum;

    private Date fecha;

    @ManyToOne
    @JsonBackReference(value = "agencia_actividad")
    @JoinColumn(referencedColumnName = "id")
    private Agencia agencia;

    @ManyToOne
    @JsonBackReference(value = "administrador_actividad")
    @JoinColumn(referencedColumnName = "id")
    private Administrador administrador ;

    @ManyToOne
    @JsonBackReference(value = "ruta_actividad")
    @JoinColumn(referencedColumnName = "id")
    private Ruta ruta;
}
