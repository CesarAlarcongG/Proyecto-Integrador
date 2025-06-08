package com.example.Proyecto_Integrador.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RutaDto {
    private int idRuta;
    private String nombre;
    private List<Integer> idAgencias;
    private int idAdministrador;
}
