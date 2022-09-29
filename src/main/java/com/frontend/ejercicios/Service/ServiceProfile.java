package com.frontend.ejercicios.Service;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Repository.RepositoryProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceProfile {

    @Autowired
    RepositoryProfile repositoryProfile;

    public Profile crearPerfil(Profile profile){
        return repositoryProfile.save(profile);
    }
    public List<Profile> consultarPerfiles(){
        List<Profile> profileList= new ArrayList<>();
        profileList.addAll(repositoryProfile.findAll());
        return profileList;
    }
    public Optional<Profile> consultarPerfil(Long id){
        return repositoryProfile.findAllById(id);
    }

    public void eliminarPerfil(Long id ){
        repositoryProfile.deleteById(id);
    }


    /*public Profile guardarperfilEmpleado (String image,String phone, String name, String email, String role, ServiceEmployee serviceEmployee, ServiceProfile serviceProfile){



        //Crear objeto Profile
        Profile profile = new Profile();
        profile.setImage(image);

        //Guardar el profile
        serviceProfile.crearPerfil(profile);

        Employee employee= new Employee();
        employee.setCreatedAt(LocalDate.now());

        employee.setId(2); //Mirar el id o pasarle uno por defecto
        profile.setEmployee(employee); //Aquí me crea la relación
        //Guardar objeto Employee
        serviceEmployee.crearUsuario(employee);


        //No creo los otros set porque ya están creados

        return profile;
    }*/
}