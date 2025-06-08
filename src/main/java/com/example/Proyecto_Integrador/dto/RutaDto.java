package com.example.Proyecto_Integrador.dto;

import com.example.Proyecto_Integrador.persistence.entity.Agencia;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RutaDto {
    private String nombre;
    private List<Integer> idAgencias;
    private int idAdministrador;
}
