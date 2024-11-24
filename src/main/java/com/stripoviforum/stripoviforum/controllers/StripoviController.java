package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comics")
public class StripoviController {

    @Autowired
    private StripoviService stripoviService;

    // GET: List all comics
    @GetMapping
    public String listComics(Model model) {
        List<Stripovi> comics = stripoviService.findAllComics(); // Fetch all comics
        model.addAttribute("comics", comics); // Add to model
        return "stripovi/Stripovi_list"; // View to display comics
    }

    // GET: View a specific comic
    @GetMapping("/{comicId}")
    public String viewComic(@PathVariable Long comicId, Model model) {
        stripoviService.findComicById(comicId).ifPresent(comic -> model.addAttribute("comic", comic));
        return "stripovi/Stripovi_view"; // View for comic details
    }

    // GET: Show form to create a new comic
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("comic", new Stripovi()); // Add empty comic object
        return "stripovi/Stripovi_form"; // View for comic creation form
    }
}
