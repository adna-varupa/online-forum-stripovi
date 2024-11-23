package com.stripoviforum.stripoviforum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stripoviforum.stripoviforum.entities.Korisnici;
import java.util.Optional;

@Repository
public interface KorisniciRepository extends JpaRepository<Korisnici, Long> {
    Optional<Korisnici> findByUsername(String username);
}
