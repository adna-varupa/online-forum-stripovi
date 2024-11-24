package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Postovi;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.PostoviService;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")  // Base path for posts
public class PostoviController {

    @Autowired
    private PostoviService postoviService;  // Service for managing posts

    @Autowired
    private StripoviService stripoviService;  // Service for managing comics

    // GET: Display all posts
    @GetMapping
    public String listAllPosts(Model model) {
        List<Postovi> posts = postoviService.findAllPosts();  // Fetch all posts
        model.addAttribute("posts", posts);  // Add posts to model
        return "postovi/Postovi_list";  // View to display posts
    }

    // GET: Display posts related to a specific comic
    @GetMapping("/comic/{comicId}")
    public String listPostsByComic(@PathVariable Long comicId, Model model) {
        List<Postovi> posts = postoviService.findPostsByComicId(comicId);  // Fetch posts for a specific comic
        model.addAttribute("posts", posts);  // Add posts to model
        model.addAttribute("comicId", comicId);  // Add comicId to model
        return "postovi/Postovi_list";  // View to display posts related to comic
    }

    @GetMapping("/new")
    public String showCreatePostForm() {
        return "postovi/Postovi_form";
    }


    @PostMapping("/new")
    public String savePost(@RequestParam Long comicId, @ModelAttribute Postovi post) {
        postoviService.savePost(post, comicId);  // Save the post with the selected comic
        return "redirect:/posts";  // Redirect to the posts list (adjust as needed)
    }

    @PostMapping("/posts/new/error")
    public String handleSavePostError(@RequestParam Long comicId, @ModelAttribute Postovi post, Model model) {
        model.addAttribute("error", "An error occurred.");
        model.addAttribute("post", post);
        model.addAttribute("comics", stripoviService.findAllComics());
        return "postovi/Postovi_form";
    }



    // GET: View a specific post by its ID
    @GetMapping("/view/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        Postovi post = postoviService.findPostById(postId);  // Fetch post by ID
        if (post != null) {
            model.addAttribute("post", post);  // Add post to model
            return "postovi/Postovi_view";  // View to display the post details
        }
        return "error";  // If post is not found, display error page
    }
}
