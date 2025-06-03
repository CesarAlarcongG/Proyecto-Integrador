package com.example.Proyecto_Integrador.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AdministradorDto {
    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private int dni;
    private int numeroLicenciaDeConducir;
}
