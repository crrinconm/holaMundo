package com.frontend.ejercicios.Repository;

import com.frontend.ejercicios.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryEmployee extends JpaRepository<Employee, Long> {

    Optional<Employee> findAllById(Long id);
    void deleteAllById(Long id);
}
