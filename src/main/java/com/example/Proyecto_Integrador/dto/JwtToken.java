package com.example.Proyecto_Integrador.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtToken {
    public JwtToken (String token){
        this.token = token;
    }
    private String token;
}
