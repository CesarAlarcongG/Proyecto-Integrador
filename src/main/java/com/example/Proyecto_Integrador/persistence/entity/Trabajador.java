package com.example.Proyecto_Integrador.persistence.entity;

import com.example.Proyecto_Integrador.persistence.entity.enums.Cargo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private int dni;

    @Enumerated(EnumType.STRING)
    private Cargo cargo;
}
