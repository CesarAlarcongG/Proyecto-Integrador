package com.example.Proyecto_Integrador.configuration.data;

import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import com.example.Proyecto_Integrador.persistence.entity.enums.Rol;
import com.example.Proyecto_Integrador.persistence.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DatosIniciales {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner administrador(AdministradorRepository administradorRepository) {
        Administrador administrador = Administrador.builder()
                .correo("root@root.root")
                .contraseña(passwordEncoder.encode("root123"))
                .rol(Rol.ADMIN)
                .build();

        return args -> {
            // Verifica si ya hay datos para evitar duplicados
            if (administradorRepository.count() == 0) {
                administradorRepository.save(administrador);

                System.out.println("Datos iniciales cargados! " +
                        "\n usuario = root" +
                        "\n contraseña = root");
            }
        };
    }
}