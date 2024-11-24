package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Korisnici;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.KorisniciService;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class KorisniciController {
    @Autowired
    private KorisniciService korisniciService;

    @Autowired
    private StripoviService stripoviService;


    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", korisniciService.findAllUsers());
        return "korisnici/Korisnici_list";
    }

    @GetMapping("/{userId}")
    public String viewUser(@PathVariable Long userId, Model model) {
        korisniciService.findUserById(userId).ifPresent(user -> model.addAttribute("user", user));
        return "korisnici/Korisnici_view";
    }

    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId, Model model) {
        korisniciService.findUserById(userId).ifPresent(user -> {
            model.addAttribute("user", user);
            model.addAttribute("comics", stripoviService.findAllComics());
        });
        return "korisnici/Korisnici_edit";
    }

    @PostMapping("/edit/{userId}")
    public String saveEditedUser(@PathVariable Long userId, @ModelAttribute Korisnici user) {
        user.setId(userId);
        korisniciService.saveUser(user, user.getComics().stream().map(Stripovi::getId).toList());
        return "redirect:/users";
    }

    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        korisniciService.deleteUser(userId);
        return "redirect:/users";
    }
}
