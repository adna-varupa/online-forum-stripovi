package com.stripoviforum.stripoviforum.repositories;

import com.stripoviforum.stripoviforum.entities.Stripovi;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StripoviRepository extends JpaRepository<Stripovi, Long> {
    // You can define custom queries here if needed, for example:
    // List<Stripovi> findByAuthor(String author);
}
