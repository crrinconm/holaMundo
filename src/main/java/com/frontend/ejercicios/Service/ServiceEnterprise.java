package com.frontend.ejercicios.Service;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Enterprise;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Enum.Enum_RoleName;
import com.frontend.ejercicios.Repository.RepositoryEnterprise;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceEnterprise {

    @Autowired
    RepositoryEnterprise repositoryEnterprise;

    public Enterprise crearEmpresa (Enterprise enterprise){
        return repositoryEnterprise.save(enterprise);
    }




    public void eliminarEmpresa(Long id){ //Eliminar por id
        repositoryEnterprise.deleteById(id);
    } //MIO

    public List<Enterprise> consultarEmpresas(){
        List<Enterprise> enterprises= new ArrayList<>(); //Creo una lista vacía para guardar las empresas
        enterprises.addAll(repositoryEnterprise.findAll()); //Que me añada a la lista todo las empresas que encuentre en las peticiones
        return enterprises;
    }

    public List<Enterprise> consultarEmpresa(){
        return repositoryEnterprise.findAll();

    }


    public Optional<Enterprise> buscarEmpresa(Long id){
        return repositoryEnterprise.findById(id);
    }



    public Enterprise guardarEmpresas (String phone, String name, String document, ServiceEnterprise serviceEnterprise){



        //Crear objeto Enterprise
        Enterprise enterprise = new Enterprise();


        enterprise.setName(name);
        enterprise.setPhone(phone);
        enterprise.setDocument(document);
        enterprise.setCreatedAt(LocalDate.now());
        enterprise.setUpdatedAt(LocalDate.now());


        //Guardar objeto Enterprise

        serviceEnterprise.crearEmpresa(enterprise);

        return enterprise;
    }

}


