package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndexPage() {
        return "index"; // Renderiza index.mustache (o index.html si usas Spring MVC con Thymeleaf)
    }
}
