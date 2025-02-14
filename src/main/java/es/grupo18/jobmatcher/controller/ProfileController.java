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
    
}
