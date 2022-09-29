package com.frontend.ejercicios.Service;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Enterprise;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Enum.Enum_RoleName;
import com.frontend.ejercicios.Repository.RepositoryEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEmployee {

    @Autowired
    RepositoryEmployee repositoryEmployee;


    //Creado para hacer la prueba con postman
    /*public void crearEmployee(Employee employee){
        repositoryEmployee.save(employee);
    }*/
    public Employee crearUsuario(Employee employee){
        return repositoryEmployee.save(employee);
    }

    public List<Employee> consultarEmpleados(){
        return repositoryEmployee.findAll();
    }

    public Optional<Employee> consultarUsuario(Long id){
        return repositoryEmployee.findById(id);
    }

    public void eliminarUsuario(Long id){ //Eliminar por id
        repositoryEmployee.deleteById(id);
    } //MIO

    //Este no se está usando
    //public void eliminar(Employee employee){ //Eliminar para pasarlo al servicio de eliminar
        //repositoryEmployee.deleteById(employee.getId());
    //}

    public Employee guardarEmpleadoperfil (String phone, String name, String email, String role, ServiceProfile serviceProfile, ServiceEmployee serviceEmployee){


        //Crear objeto Profile
        Profile profile= new Profile();
        profile.setPhone(phone);
        profile.setCreatedAt(LocalDate.now());
        profile.setUpdatedAt(LocalDate.now());


        //Guardar objeto profile
        serviceProfile.crearPerfil(profile);

        //Crear objeto Employee
        Employee employee= new Employee();
        employee.setProfile(profile);
        employee.setName(name);
        employee.setEmail(email);
        employee.setCreatedAt(LocalDate.now());
        employee.setUpdatedAt(LocalDate.now());

        Enterprise enterprise= new Enterprise();
        enterprise.setId(1);

        employee.setEnterprise(enterprise);


        if(role.equals("Admin")){
            employee.setRole(Enum_RoleName.Admin);
        } else if(role.equals("Operario")){
            employee.setRole(Enum_RoleName.Operario);
        }

        //Guardar objeto Employee
        serviceEmployee.crearUsuario(employee);
        return employee;
    }
}
