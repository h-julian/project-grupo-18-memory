package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.grupo18.jobmatcher.model.User;
import java.util.Arrays;

@Controller
public class ProfileController {

    // Usuario en memoria
    private static final User user = new User(
        1L, 
        "Juan Pérez", 
        "juan.perez@email.com", 
        "password123",
        "123456789",
        "Madrid España",
        "Apasionado por la tecnología y la programación.",
        5,
        Arrays.asList("Grado en Ingeniería Informática", "Máster en Ciberseguridad"),
        Arrays.asList("Java", "Spring Boot", "SQL", "Pentesting"),
        "/img/custom.jpg"
    );

    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("user", user);
        model.addAttribute("studies", user.getDegrees()); 
        model.addAttribute("skills", user.getSkills());
        model.addAttribute("experience", user.getExperience());
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", user); // Usa el usuario en memoria
        return "profileEditor";
    }

    @PostMapping("/profile/edit")
    public String saveProfile(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
                              @RequestParam String location, @RequestParam String about) {
        // Actualiza los valores en memoria
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLocation(location);
        user.setBio(about);
        
        return "redirect:/profile";
    }

    @GetMapping("/profile/form")
    public String showForm() {
        return "profileForm";
    }
}
