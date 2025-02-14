package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MatchController {

    @GetMapping("/currentMatch")
    public String showMatchPage(Model model) {
        model.addAttribute("message", "Aquí tienes tus matches");
        return "currentMatch"; 
    }
}
