package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.repositories.StripoviRepository;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@Service
public class StripoviService {
    @Autowired
    private StripoviRepository stripoviRepository;

    public List<Stripovi> findAllComics() {
        return stripoviRepository.findAll();
    }

    // Fix here: Call the method on the stripoviRepository object
    public Optional<Stripovi> findComicById(Long id) {
        return stripoviRepository.findById(id);  // Corrected this line
    }

    public Stripovi saveComic(Stripovi comic) {
        return stripoviRepository.save(comic);
    }

    public void deleteComic(Long id) {
        stripoviRepository.deleteById(id);
    }
}
