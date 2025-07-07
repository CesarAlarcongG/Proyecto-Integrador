package com.example.Proyecto_Integrador.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConductorDto {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private int numLicenciaConducir;
}
