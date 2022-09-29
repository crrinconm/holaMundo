package com.frontend.ejercicios.Repository;

import com.frontend.ejercicios.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTransaction extends JpaRepository<Transaction, Long> {

}
