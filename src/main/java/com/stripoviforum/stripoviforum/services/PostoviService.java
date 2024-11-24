package com.stripoviforum.stripoviforum.services;

import com.stripoviforum.stripoviforum.controllers.PostoviController;
import com.stripoviforum.stripoviforum.entities.Postovi;
import com.stripoviforum.stripoviforum.repositories.PostoviRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Arrays;

@Service
public class PostoviService {

    @Autowired
    private PostoviRepository postoviRepository;

    @Autowired
    private StripoviService stripoviService;

    public void savePost(Postovi post) {
        // Here you can add additional logic if needed, e.g., validation
        postoviRepository.save(post);  // This saves the post to the database
    }
    public List<Postovi> getAllPosts() {
        return postoviRepository.findAll();  // Assuming eager loading is set up, this should work
    }
    public Postovi getPostById(Long postId) {
        // Using the injected repository instance to call findById
        return postoviRepository.findById(postId).orElse(null);  // Return null if not found
    }

}