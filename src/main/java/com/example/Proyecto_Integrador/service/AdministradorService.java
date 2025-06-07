package com.example.Proyecto_Integrador.service;

import com.example.Proyecto_Integrador.dto.AdministradorDto;
import com.example.Proyecto_Integrador.dto.CredencialesDto;
import com.example.Proyecto_Integrador.dto.JwtToken;
import com.example.Proyecto_Integrador.dto.RespuestaLoginDto;
import com.example.Proyecto_Integrador.persistence.entity.Actividad;
import com.example.Proyecto_Integrador.persistence.entity.Administrador;
import com.example.Proyecto_Integrador.persistence.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {
    @Autowired
    private AdministradorRepository administradorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public Administrador registrarAdministrador(AdministradorDto administradorDto){

        //1. Mapear de DTO a Clase
        Administrador administrador = mapearAdministrador(administradorDto);

        //2. Guardar en la BD
        return administradorRepository.save(administrador);
    }

    public RespuestaLoginDto repuestaLogin(Authentication authentication){

        return RespuestaLoginDto.builder()
                .administrador( administradorRepository.findByCorreo(authentication.getName()).get())
                .jwtToken(retornarToken(authentication))
                .build();
    }

    public JwtToken retornarToken(Authentication authentication){
        Administrador administrador = administradorRepository.findByCorreo(authentication.getName())
                        .orElseThrow(() -> new RuntimeException("No se identifico este correo en la BD ==" +authentication.getName() ));
        String token = jwtService.getToken(administrador);
        return new JwtToken(token);
    }

    public Authentication autenticarUsuario(CredencialesDto credencialesDto) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                credencialesDto.getCorreo(),
                credencialesDto.getContraseña()
        );

        return authenticationManager.authenticate(authToken);
    }


    public Administrador obtenerPorId(int id){
        return administradorRepository.findById(id).get();
    }


    /// ////////////////////////////////////////////////////////////////////////////////
    public Administrador mapearAdministrador(AdministradorDto administradorDto){
        return Administrador.builder()
                .nombre(administradorDto.getNombre())
                .apellido(administradorDto.getApellido())
                .correo(administradorDto.getCorreo())
                .contraseña(passwordEncoder.encode(administradorDto.getContraseña()))
                .dni(administradorDto.getDni())
                .numeroLicenciaDeConducir(administradorDto.getNumeroLicenciaDeConducir())
                .build();
    }

    public Administrador agregarActividad(Administrador administrador, Actividad actividad){
        List<Actividad> actividadList = administrador.getActividades();
        actividadList.add(actividad);
        administrador.setActividades(actividadList);
        return administradorRepository.save(administrador);
    }
}
