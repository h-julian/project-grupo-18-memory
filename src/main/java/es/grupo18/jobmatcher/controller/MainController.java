package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/main")
    public String showMainPage(Model model) {
        model.addAttribute("message", "Bienvenido a la página principal");
        return "main"; 
    }
}

