package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class MainController {

    @GetMapping("/main")
    public String showMainPage(Model model) {
        model.addAttribute("message", "Bienvenido a la página principal");
        return "main"; // Devuelve el nombre de la plantilla HTML
    }
}

