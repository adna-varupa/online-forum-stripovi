package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StripoviController {
    @Autowired

    @GetMapping
    public String listComics(Model model) {
<<<<<<< HEAD
=======
        List<Stripovi> comics = stripoviService.findAllComics();  // Dohvati sve stripove
        model.addAttribute("comics", comics);  // Dodaj stripove u model
        return "stripovi/Stripovi_list";  // Pogled za prikaz svih stripova
>>>>>>> 8e7b4ba96a7ebf45c4989fe9c42dc77bcd7f9563
    }

    @GetMapping("/{comicId}")
    public String viewComic(@PathVariable Long comicId, Model model) {
<<<<<<< HEAD
    }

    }
}
=======
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

>>>>>>> 8e7b4ba96a7ebf45c4989fe9c42dc77bcd7f9563
