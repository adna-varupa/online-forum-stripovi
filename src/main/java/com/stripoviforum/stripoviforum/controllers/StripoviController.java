package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comics")
public class StripoviController {
    @Autowired
    private StripoviService stripoviService;

    @GetMapping
    public String listComics(Model model) {
        model.addAttribute("comics", stripoviService.findAllComics());
        return "comic_list";
    }

    @GetMapping("/{comicId}")
    public String viewComic(@PathVariable Long comicId, Model model) {
        stripoviService.findComicById(comicId).ifPresent(comic -> model.addAttribute("comic", comic));
        return "comic_view";
    }

    @GetMapping("/edit/{comicId}")
    public String editComic(@PathVariable Long comicId, Model model) {
        stripoviService.findComicById(comicId).ifPresent(comic -> model.addAttribute("comic", comic));
        return "comic_edit";
    }

    @PostMapping("/edit/{comicId}")
    public String saveEditedComic(@PathVariable Long comicId, @ModelAttribute Stripovi comic) {
        comic.setId(comicId);
        stripoviService.saveComic(comic);
        return "redirect:/comics";
    }

    @GetMapping("/delete/{comicId}")
    public String deleteComic(@PathVariable Long comicId) {
        stripoviService.deleteComic(comicId);
        return "redirect:/comics";
    }
}
