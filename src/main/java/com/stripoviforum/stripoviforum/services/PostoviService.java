package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.entities.Postovi;
import com.stripoviforum.stripoviforum.repositories.PostoviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostoviService {

    @Autowired
    private PostoviRepository postoviRepository;

    @Autowired
    private StripoviService stripoviService;

    public void savePost(Postovi post, Long comicId) {
        if (comicId != null) {
            stripoviService.findComicById(comicId).ifPresent(comic -> post.setStripovi(comic));
        }
        postoviRepository.save(post);  // Save the post to the repository
    }

    public List<Postovi> findAllPosts() {
        return postoviRepository.findAll();
    }


}
