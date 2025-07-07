package com.example.Proyecto_Integrador.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajadorDto {
    private int id;
    private String nombre;
    private String apellido;
    private int dni;
    private String cargo;
}
