package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {

    @GetMapping("/blog")
    public String mostrarBlog() {
        return "blog";
    }
    
    @GetMapping("/newArticle")
    public String mostrarNewArticle() {
        return "newArticle";
    }
}
