package com.stripoviforum.stripoviforum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stripoviforum.stripoviforum.entities.Stripovi;

@Repository
public interface StripoviRepository extends JpaRepository<Stripovi, Long> {
}
