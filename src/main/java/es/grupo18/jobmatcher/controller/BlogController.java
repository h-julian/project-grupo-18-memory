package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {

    @GetMapping("/blog")
    public String showBlogPage(Model model) {
        // Aquí en el futuro se cargarán los posts desde la base de datos
        return "blog";
    }

    @GetMapping("/newpost")
    public String showNewPostForm() {
        return "newpost";
    }

    @PostMapping("/newpost")
    public String createNewPost(@RequestParam String title, @RequestParam String content) {
        // Aquí en el futuro se guardará el post en la base de datos
        return "redirect:/blog"; // Redirige al blog después de postear
    }
}
