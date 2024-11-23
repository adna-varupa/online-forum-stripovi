package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.repositories.KorisniciRepository;
import com.stripoviforum.stripoviforum.repositories.StripoviRepository;
import com.stripoviforum.stripoviforum.entities.Korisnici;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class KorisniciService {
    @Autowired
    private KorisniciRepository korisniciRepository;

    @Autowired
    private StripoviRepository stripoviRepository;

    public List<Korisnici> findAllUsers() {
        return korisniciRepository.findAll();
    }

    public Optional<Korisnici> findUserById(Long id) {
        return korisniciRepository.findById(id);
    }

    public Korisnici saveUser(Korisnici user, List<Long> comicIds) {
        if (comicIds != null) {
            for (Long comicId : comicIds) {
                stripoviRepository.findById(comicId).ifPresent(user.getComics()::add);
            }
        }
        return korisniciRepository.save(user);
    }

    public Korisnici updateUser(Korisnici updatedUser, List<Long> comicIds) {
        Optional<Korisnici> existingUserOpt = korisniciRepository.findById(updatedUser.getId());
        if (existingUserOpt.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + updatedUser.getId() + " not found.");
        }

        Korisnici existingUser = existingUserOpt.get();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());

        existingUser.getComics().clear();
        if (comicIds != null) {
            for (Long comicId : comicIds) {
                stripoviRepository.findById(comicId).ifPresent(existingUser.getComics()::add);
            }
        }

        return korisniciRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        korisniciRepository.deleteById(id);
    }
}
