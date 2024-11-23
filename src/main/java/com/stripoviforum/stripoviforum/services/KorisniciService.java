package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.entities.Korisnici;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.repositories.KorisniciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KorisniciService {
    @Autowired
    private KorisniciRepository korisniciRepository;

    @Autowired
    private StripoviService stripoviService;

    public List<Korisnici> findAllUsers() {
        return korisniciRepository.findAll();
    }

    public Optional<Korisnici> findUserById(Long id) {
        return korisniciRepository.findById(id);
    }

    public Korisnici saveUser(Korisnici user, List<Long> comicIds) {
        List<Stripovi> comics = comicIds.stream()
                .map(stripoviService::findComicById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        user.setComics(comics);
        return korisniciRepository.save(user);
    }

    public void deleteUser(Long id) {
        korisniciRepository.deleteById(id);
    }
}
