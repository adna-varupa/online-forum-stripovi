package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Postovi;
import com.stripoviforum.stripoviforum.repositories.PostoviRepository;
import com.stripoviforum.stripoviforum.services.KorisniciService;
import com.stripoviforum.stripoviforum.services.PostoviService;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PostoviController {

    @Autowired
    private PostoviService postoviService;
    @Autowired
    private KorisniciService korisniciService;


    @Autowired
    private StripoviService stripoviService;
    @Autowired
    private PostoviRepository postoviRepository;

    @GetMapping("/posts")
    public String getPosts(Model model) {
        List<Postovi> posts = postoviService.getAllPosts();
        model.addAttribute("posts", posts);
        return "postovi/Postovi_list";
    }

    // Show the form to create a new post
    @GetMapping("/posts/new")
    public String showCreatePostForm(Model model) {
        model.addAttribute("post", new Postovi());  // Add an empty Postovi object for form binding
        model.addAttribute("comics", stripoviService.findAllComics());  // Add list of comics for selection
        model.addAttribute("users", korisniciService.getAllUsers());  // Add list of users
        return "postovi/Postovi_form";  // This will look for a Thymeleaf template
    }


    // Handle the form submission to create a new post
    @PostMapping("/posts/new")
    public String savePost(@RequestParam Long comicId, @ModelAttribute Postovi post) {
        postoviService.savePost(post, comicId);  // Save the post with the selected comic
        return "redirect:/posts";  // Redirect to the posts list (or wherever you want)
    }

}