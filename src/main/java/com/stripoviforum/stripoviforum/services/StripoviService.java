package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.repositories.StripoviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StripoviService {

    @Autowired
    private StripoviRepository stripoviRepository;

    public List<Stripovi> findAllComics() {
        return stripoviRepository.findAll();
    }

    public Optional<Stripovi> findComicById(Long id) {
        return stripoviRepository.findById(id);
    }

    public Stripovi saveComic(Stripovi comic) {
        return stripoviRepository.save(comic);
    }

    public void deleteComic(Long id) {
        stripoviRepository.deleteById(id);
    }
    public Stripovi getComicById(Long id) {
        return stripoviRepository.findById(id).orElseThrow(() -> new RuntimeException("Comic not found"));
    }
}