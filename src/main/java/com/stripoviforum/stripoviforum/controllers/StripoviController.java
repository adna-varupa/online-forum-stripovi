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

    // GET: Show the form to create a new comic
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("comic", new Stripovi());
        return "stripovi/Stripovi_form"; // Render the form
    }

    // POST: Save new comic with manual validation
    @PostMapping("/new")
    public String saveNewComic(@ModelAttribute Stripovi comic, Model model) {

        // Manual validation
        if (comic.getTitle() == null || comic.getTitle().isEmpty()) {
            model.addAttribute("titleError", "Title cannot be blank.");
            return "stripovi/Stripovi_form"; // Return the form with error message
        }

        if (comic.getTitle().length() > 100) {
            model.addAttribute("titleError", "Title must not exceed 100 characters.");
            return "stripovi/Stripovi_form";
        }

        if (comic.getAuthor() == null || comic.getAuthor().isEmpty()) {
            model.addAttribute("authorError", "Author cannot be blank.");
            return "stripovi/Stripovi_form";
        }

        if (comic.getAuthor().length() > 50) {
            model.addAttribute("authorError", "Author must not exceed 50 characters.");
            return "stripovi/Stripovi_form";
        }

        if (comic.getDescription() != null && comic.getDescription().length() > 500) {
            model.addAttribute("descriptionError", "Description must not exceed 500 characters.");
            return "stripovi/Stripovi_form";
        }

        // Save the comic if validation passes
        stripoviService.saveComic(comic);
        return "redirect:/comics"; // Redirect to the comic list
    }

    // GET: Show form to edit an existing comic
    @GetMapping("/edit/{comicId}")
    public String editComic(@PathVariable Long comicId, Model model) {
        stripoviService.findComicById(comicId).ifPresent(comic -> model.addAttribute("comic", comic));
        return "stripovi/Stripovi_edit"; // View for comic editing form
    }

    // POST: Save edited comic
    @PostMapping("/edit/{comicId}")
    public String saveEditedComic(@PathVariable Long comicId, @ModelAttribute Stripovi comic) {
        comic.setId(comicId); // Ensure the comic ID is retained
        stripoviService.saveComic(comic); // Save updated comic
        return "redirect:/comics"; // Redirect to the comic list
    }

    // GET: Delete a comic
    @GetMapping("/delete/{comicId}")
    public String deleteComic(@PathVariable Long comicId) {
        stripoviService.deleteComic(comicId); // Delete the comic
        return "redirect:/comics"; // Redirect to the comic list
    }
}
