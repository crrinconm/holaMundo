package com.frontend.ejercicios.Controller;

import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Service.ServiceProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ControllerProfile {

    @Autowired
    ServiceProfile serviceProfile;

    @PostMapping("/profile")
    public void crearPerfil(@RequestBody Profile profile){
        serviceProfile.crearPerfil(profile);
    }

    @GetMapping("/profile")
    public List<Profile> consultarPerfiles(){
        return serviceProfile.consultarPerfiles();
    }

    @GetMapping("/profile/{id}")
    public Optional<Profile> consultarPerfil(@PathVariable("id") Long id){
        return serviceProfile.consultarPerfil(id);
    }

    @DeleteMapping("/profile/{id}")
    public void eliminarPerfil(@PathVariable ("id") Long id){
        serviceProfile.eliminarPerfil(id);
    }

    @PatchMapping("/profile")
    public void editarPerfil(@RequestBody Profile profile){
        serviceProfile.crearPerfil(profile);
    }
}
