package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.repositories.PostoviRepository;
import com.stripoviforum.stripoviforum.entities.Postovi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostoviService {

    @Autowired
    private PostoviRepository postoviRepository;

    @Autowired
    private StripoviService stripoviService;

    public List<Postovi> findAllPosts() {
        return postoviRepository.findAll();
    }

    public List<Postovi> findPostsByComicId(Long comicId) {
        return postoviRepository.findByComicId(comicId);
    }

    public Postovi savePost(Postovi post, Long comicId) {
        if (comicId != null) {
            stripoviService.findComicById(comicId).ifPresent(comic -> post.setStripovi(comic));
        }
        return postoviRepository.save(post);
    }

    public void deletePost(Long id) {
        postoviRepository.deleteById(id);
    }
}