package com.frontend.ejercicios.Entity;

import com.frontend.ejercicios.Enum.Enum_RoleName;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import javax.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Employee")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name = "name") //Lo agregué porque no estaba en la entidad y lo necesitaba en la tabla
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Column(name= "role")
    @Enumerated (EnumType.STRING)
    private Enum_RoleName role;


    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;


    @OneToMany(mappedBy = "id")
    private List<Transaction> transactions;


    @Column(name= "updatedAt")
    private LocalDate updatedAt;

    @Column(name = "createdAt")
    private LocalDate createdAt;


}
