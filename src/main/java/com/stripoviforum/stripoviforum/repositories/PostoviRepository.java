package com.stripoviforum.stripoviforum.repositories;

import com.stripoviforum.stripoviforum.entities.Postovi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostoviRepository extends JpaRepository<Postovi, Long> {
    // Custom queries can be added here
}
