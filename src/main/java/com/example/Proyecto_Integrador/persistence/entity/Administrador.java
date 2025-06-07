package com.example.Proyecto_Integrador.persistence.entity;

import com.example.Proyecto_Integrador.persistence.entity.enums.Rol;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Administrador implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String apellido;
    private String correo;
    private String contraseña;
    private int dni;
    private int numeroLicenciaDeConducir;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(mappedBy = "administrador")
    @JsonManagedReference(value = "administrador_actividad")
    private List<Actividad> actividades;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rol.getAuthorities();
    }

    @Override
    public String getPassword() {
        return contraseña;
    }

    @Override
    public String getUsername() {
        return correo;
    }
}
