package com.frontend.ejercicios.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="Profile")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Profile {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "image")
    private String image;

    @Column(name = "phone")
    private String phone;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) //Esta anotación me dice que al eliminar un registro (nos elimina las relaciones)
    @JoinColumn(name= "user_id") //El JoinColumn me ayuda a darle el nombre a la clave foranea
    private Employee employee;

    @Column(name= "createdAt")
    private LocalDate createdAt;

    @Column(name= "updatedAt")
    private LocalDate updatedAt;

}
