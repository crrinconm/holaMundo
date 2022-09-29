package com.frontend.ejercicios.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Transaction")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "concept")
    private String concept;

    @Column(name = "amount")
    private float amount;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE) //Lo cambié a Merge porque ahí dice que puede solucionar el problema
    @JoinColumn(name= "user_id")
    private Employee user;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "enterprise_id") //En h2-console me da el nombre en mayuscula
    private Enterprise enterprise;

    @Column(name = "createdAt")
    private LocalDate createdAt;

    @Column(name = "updatedAt")
    private LocalDate updatedAt;

}
