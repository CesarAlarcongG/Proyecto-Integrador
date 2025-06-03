package com.example.Proyecto_Integrador.persistence.entity.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public enum Rol {

    ADMIN(Set.of("GESTIONAR_USUARIOS", "GESTIONAR_RUTAS", "GESTIONAR_HORARIOS", "VER_VIAJES", "GESTIONAR_ADMINISTRADORES"));

    private final Set<String> permisos;

    Rol(Set<String> permisos) {
        this.permisos = permisos;
    }

    public Set<String> getPermisos() {
        return permisos;
    }

    public boolean tienePermiso(String permiso) {
        return permisos.contains(permiso);
    }
    public List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Agregar el rol como autoridad (prefijo ROLE_)
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        // Agregar los permisos como autoridades
        authorities.addAll(
                permisos.stream()
                        .map(permiso -> new SimpleGrantedAuthority(permiso))
                        .toList()
        );

        return authorities;
    }
}