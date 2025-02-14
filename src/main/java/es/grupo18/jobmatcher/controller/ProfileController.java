package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showOptionsPage(Model model) {
        return "profile";
    }
    
    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        // Agrega atributos al modelo, si es necesario, para prellenar información del perfil actual
        return "profileEditor"; // Se asume que existe un template profileEditor.html en templates
    }
}
