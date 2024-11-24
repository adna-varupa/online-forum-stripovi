package com.stripoviforum.stripoviforum.repositories;

import com.stripoviforum.stripoviforum.entities.Postovi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PostoviRepository extends JpaRepository<Postovi, Long> {
    // Additional custom queries can be defined here, if needed
}