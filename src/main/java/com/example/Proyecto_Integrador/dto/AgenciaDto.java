package com.example.Proyecto_Integrador.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AgenciaDto {
    private String departamento;
    private String provincia;
    private String dirección;
    private String referencia;
}
