package com.frontend.ejercicios.Repository;

import com.frontend.ejercicios.Entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryProfile extends JpaRepository<Profile, Long> {
    Optional<Profile> findAllById(Long id);
}
