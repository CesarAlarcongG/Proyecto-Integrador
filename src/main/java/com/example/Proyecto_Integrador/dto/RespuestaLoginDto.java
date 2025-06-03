package com.example.Proyecto_Integrador.dto;

import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RespuestaLoginDto {
    private Administrador administrador;
    private JwtToken jwtToken;
}
