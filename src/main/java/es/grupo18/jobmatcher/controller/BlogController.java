package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Post;
import es.grupo18.jobmatcher.service.AccountService;
import es.grupo18.jobmatcher.service.PostService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final AccountService accountService;
    private final PostService postService;

    public BlogController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @GetMapping("") // Muestra todos los posts disponibles
    public String showBlogPage(Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        model.addAttribute("posts", postService.getAllPosts());
        model.addAttribute("timestamp", formatter);

        return "blog";
    }

    @GetMapping("/newpost") // Muestra el formulario para crear un nuevo post
    public String showNewPostForm() {
        return "newpost";
    }

    @PostMapping("/newpost") // Crea un nuevo post
    public String createNewPost(@RequestParam String title,
                                @RequestParam String content,
                                @RequestParam(required = false) String imagePath) {

        Account currentUser = accountService.findAccountById(1L); // Usuario actual en fase 1

        if (currentUser == null) {
            throw new IllegalStateException("No se encontró la cuenta con ID 1L. Asegúrate de que exista.");
        }

        Post newPost = new Post(title, content, LocalDateTime.now(), imagePath, currentUser);

        currentUser.addPost(newPost);
        postService.addPost(newPost);

        return "redirect:/blog";
    }
}
