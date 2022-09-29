package com.frontend.ejercicios.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Enterprise")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name= "name", unique = true)
    private String name;

    @Column(name = "document", unique = true)
    private String document;

    @Column(name = "phone")
    private String phone;

    @Column(name= "address")
    private String address;


    @OneToMany(mappedBy = "id") //Se relaciona por medio de la clave primaria que en este caso sería la foranea en users
    private List<Employee> users;


    @OneToMany(mappedBy = "id")
    private List<Transaction> transactions;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;
}
