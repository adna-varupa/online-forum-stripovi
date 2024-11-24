package com.stripoviforum.stripoviforum.controllers;


import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comics")  // Putanja za pristup stripovima
public class StripoviController {

    @Autowired
    private StripoviService stripoviService;  // Servis za upravljanje stripovima

    // GET: Prikaz svih stripova
    @GetMapping
    public String listComics(Model model) {
        List<Stripovi> comics = stripoviService.findAllComics();  // Dohvati sve stripove
        model.addAttribute("comics", comics);  // Dodaj stripove u model
        return "stripovi/Stripovi_list";  // Pogled za prikaz svih stripova
    }

    // GET: Prikaz detalja o jednom stripu
    @GetMapping("/{comicId}")
    public String viewComic(@PathVariable Long comicId, Model model) {
        stripoviService.findComicById(comicId).ifPresent(comic -> model.addAttribute("comic", comic));  // Dohvati strip po ID-u
        return "stripovi/Stripovi_view";  // Pogled za detalje stripa
    }

    // GET: Prikaz obrasca za dodavanje novog stripa
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("comic", new Stripovi());  // Dodaj novi prazni strip u model
        return "stripovi/Stripovi_form";  // Pogled za obrazac za kreiranje novog stripa
    }
}

