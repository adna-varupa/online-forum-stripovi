package com.stripoviforum.stripoviforum.controllers;

import com.stripoviforum.stripoviforum.entities.Korisnici;
import com.stripoviforum.stripoviforum.entities.Stripovi;
import com.stripoviforum.stripoviforum.services.KorisniciService;
import com.stripoviforum.stripoviforum.services.StripoviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class KorisniciController {

    @Autowired
    private KorisniciService korisniciService;

    @Autowired
    private StripoviService stripoviService;

    // List all users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", korisniciService.findAllUsers());
        return "korisnici/Korisnici_list";
    }

    // View details of a specific user
    @GetMapping("/{userId}")
    public String viewUser(@PathVariable Long userId, Model model) {
        Optional<Korisnici> userOpt = korisniciService.findUserById(userId);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "korisnici/Korisnici_view";
        } else {
            model.addAttribute("error", "User not found");
            return "error";
        }
    }

    // Edit user form
    @GetMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId, Model model) {
        Optional<Korisnici> userOpt = korisniciService.findUserById(userId);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            model.addAttribute("comics", stripoviService.findAllComics());
            return "korisnici/Korisnici_edit";
        } else {
            model.addAttribute("error", "User not found");
            return "error";
        }
    }

    // Save the edited user
    @PostMapping("/edit/{userId}")
    public String saveEditedUser(@PathVariable Long userId, @ModelAttribute Korisnici user) {
        Optional<Korisnici> existingUserOpt = korisniciService.findUserById(userId);
        if (existingUserOpt.isPresent()) {
            user.setId(userId);
            korisniciService.saveUser(user, user.getComics().stream().map(Stripovi::getId).toList());
            return "redirect:/users";
        } else {
            return "redirect:/error";  // Redirect to an error page if the user is not found
        }
    }

    // Delete user
    @GetMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        Optional<Korisnici> userOpt = korisniciService.findUserById(userId);
        if (userOpt.isPresent()) {
            korisniciService.deleteUser(userId);
            return "redirect:/users";
        } else {
            return "redirect:/error";  // Redirect to an error page if the user is not found
        }
    }
}
