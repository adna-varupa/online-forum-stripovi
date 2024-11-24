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
    }

    @GetMapping("/{comicId}")
    public String viewComic(@PathVariable Long comicId, Model model) {
    }

    }
}
