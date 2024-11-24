package com.stripoviforum.stripoviforum.repositories;

import com.stripoviforum.stripoviforum.entities.Stripovi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StripoviRepository extends JpaRepository<Stripovi, Long> {
    // Custom queries can be added here
}
