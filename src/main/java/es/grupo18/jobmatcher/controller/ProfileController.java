package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.grupo18.jobmatcher.model.User;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        User user = User.loadUser(); // Cargar el único usuario
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        User user = User.loadUser(); // Cargar el único usuario
        model.addAttribute("user", user);
        return "profileEditor";
    }

    @PostMapping("/profile/edit")
    public String saveProfile(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
                              @RequestParam String location, @RequestParam String about) {
        User user = User.loadUser(); // Cargar el único usuario
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLocation(location);
        user.setBio(about);
        User.saveUser(user); // Guardar los cambios en el archivo JSON
        return "redirect:/profile";
    }
}