package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Postovi;
import com.stripoviforum.stripoviforum.services.PostoviService;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")  // Putanja za pristup postovima
public class PostoviController {

    @Autowired
    private PostoviService postoviService;  // Servis za upravljanje postovima

    @Autowired
    private StripoviService stripoviService;  // Servis za upravljanje stripovima

    // GET: Prikaz svih postova vezanih za određeni strip
    @GetMapping("/comic/{comicId}")
    public String listPostsByComic(@PathVariable Long comicId, Model model) {
        List<Postovi> posts = postoviService.findPostsByComicId(comicId);  // Dohvati postove za određeni strip
        model.addAttribute("posts", posts);  // Dodaj postove u model
        model.addAttribute("comicId", comicId);  // Dodaj ID stripa u model
        return "post_list";  // Pogled za prikaz postova
    }

    // GET: Prikaz obrasca za dodavanje novog posta
    @GetMapping("/new/{comicId}")
    public String showCreateForm(@PathVariable Long comicId, Model model) {
        model.addAttribute("post", new Postovi());  // Dodaj novi prazni post u model
        model.addAttribute("comicId", comicId);  // Dodaj ID stripa u model
        return "post_form";  // Pogled za obrazac za kreiranje posta
    }

    // POST: Spremi novi post vezan za strip
    @PostMapping("/new/{comicId}")
    public String savePost(@PathVariable Long comicId, @ModelAttribute Postovi post) {
        postoviService.savePost(post, comicId);  // Spremi post koristeći servis
        return "redirect:/posts/comic/" + comicId;  // Preusmjeri na prikaz postova za odabrani strip
    }

    @GetMapping("/view/{postId}")
    public String viewPost(@PathVariable Long postId, Model model) {
        Postovi post = postoviService.findPostById(postId);  // Dohvati post po ID-u
        if (post != null) {
            model.addAttribute("post", post);  // Dodaj post u model
            return "post_view";  // Prikazivanje stranice za post
        }
        return "error";  // Ako post nije pronađen, prikaži stranicu s greškom
    }
}
