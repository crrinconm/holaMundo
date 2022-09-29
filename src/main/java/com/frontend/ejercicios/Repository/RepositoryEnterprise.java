package com.frontend.ejercicios.Repository;


import com.frontend.ejercicios.Entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryEnterprise extends JpaRepository<Enterprise,Long> {
}
