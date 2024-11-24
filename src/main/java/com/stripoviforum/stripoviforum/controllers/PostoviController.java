package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Korisnici;
import com.stripoviforum.stripoviforum.entities.Postovi;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.repositories.PostoviRepository;
import com.stripoviforum.stripoviforum.services.KorisniciService;
import com.stripoviforum.stripoviforum.services.PostoviService;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String savePost(@RequestParam Long userId, @RequestParam Long comicId, @ModelAttribute Postovi post) {
        // Set the user and comic from the form data
        Korisnici korisnik = korisniciService.getUserById(userId); // Ensure you have a method like this in your UserService
        Stripovi strip = stripoviService.getComicById(comicId); // Similarly, a method in ComicService to get a Comic

        // Set the user and comic to the post
        post.setKorisnik(korisnik);
        post.setStripovi(strip);

        postoviService.savePost(post);  // Save the post (assuming PostoviService is saving it properly)

        return "redirect:/posts";  // Redirect to the posts list or other page
    }

    @GetMapping("/posts/{postId}")
    public String viewPostDetails(@PathVariable Long postId, Model model) {
        Postovi post = postoviService.getPostById(postId);  // Fetch the post details using the postId
        model.addAttribute("post", post);  // Add post to the model
        return "postovi/Postovi_view";  // The Thymeleaf template to render the post details
    }

    @PostMapping("/deletePost/{id}")
    public String deletePost(@PathVariable("id") Long id) {
        postoviService.deletePostById(id);
        return "redirect:/posts";  // Redirect to the list of posts after deletion
    }

    @GetMapping("/posts/edit/{postId}")
    public String editPost(@PathVariable("postId") Long postId, Model model) {
        Postovi post = postoviService.getPostById(postId);
        model.addAttribute("post", post);
        return "postovi/Postovi_edit"; // editPost.html is your edit page template
    }

    // Handle post editing
    @PostMapping("/posts/edit/{postId}")
    public String updatePost(@PathVariable("postId") Long postId, @RequestParam("content") String content) {
        postoviService.updatePost(postId, content);  // Update post with new content
        return "redirect:/posts";  // Redirect to the list of posts
    }

}