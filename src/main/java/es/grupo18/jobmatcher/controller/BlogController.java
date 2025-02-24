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
        // Future posts will be retrieved from the database here
        return "blog";
    }

    @GetMapping("/newpost")
    public String showNewPostForm() {
        return "newpost";
    }

    @PostMapping("/newpost")
    public String createNewPost(@RequestParam String title, @RequestParam String content) {
        // Future posts will be retrieved from the database here
        return "redirect:/blog"; // Redirects to the blog page after creating the new post
    }

}
