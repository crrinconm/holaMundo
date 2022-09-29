package com.frontend.ejercicios.Service;

import com.frontend.ejercicios.Entity.Employee;
import com.frontend.ejercicios.Entity.Enterprise;
import com.frontend.ejercicios.Entity.Profile;
import com.frontend.ejercicios.Entity.Transaction;
import com.frontend.ejercicios.Enum.Enum_RoleName;
import com.frontend.ejercicios.Repository.RepositoryTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceTransaction {

    @Autowired
    RepositoryTransaction repositoryTransaction;

    public void crearMovimientos(Transaction transaction){
        repositoryTransaction.save(transaction);
    }

    public Transaction crearIngresos(Transaction transaction){
        return repositoryTransaction.save(transaction);
    }

    public List<Transaction> consultarTransacciones (){
        return repositoryTransaction.findAll();
    }


    public void eliminarTransacciones(Long id){
        repositoryTransaction.deleteById(id);
    }


    public Optional<Transaction> consultarMovimientos(Long id){
        return repositoryTransaction.findById(id);
    }


    public Transaction guardarIngresos (Float amount, String concept, LocalDate createdAt, ServiceEmployee serviceEmployee, ServiceEnterprise serviceEnterprise, ServiceTransaction serviceTransaction){


        //Crear objeto Employee
        Employee user= new Employee();
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());

        //Guardar
        serviceEmployee.crearUsuario(user);

        Enterprise enterprise= new Enterprise();
        enterprise.setCreatedAt(LocalDate.now());
        enterprise.setUpdatedAt(LocalDate.now());


        //Crear objeto Transaction
        Transaction transaction= new Transaction();
        transaction.setUser(user);
        transaction.setEnterprise(enterprise);
        transaction.setAmount(amount);
        transaction.setConcept(concept);
        transaction.setCreatedAt(LocalDate.now());


        //Guardar objeto Transaction
        serviceTransaction.crearIngresos(transaction);
        return transaction;
    }

}
