package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/blog")
    public String mostrarBlog() {
        return "blog"; // Se asume que blog.html se encuentra en el directorio templates
    }
    
    @GetMapping("/newArticle")
    public String mostrarNewArticle() {
        return "newArticle"; // Retorna newArticle.html ubicado en templates
    }
}
