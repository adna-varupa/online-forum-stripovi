package com.stripoviforum.stripoviforum.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.stripoviforum.stripoviforum.entities.Postovi;
import java.util.List;

@Repository
public interface PostoviRepository extends JpaRepository<Postovi, Long> {
    List<Postovi> findByStripoviId(Long stripoviId);
}
