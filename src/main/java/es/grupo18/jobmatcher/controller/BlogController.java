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

    @GetMapping("") // Shows all available blogs
    public String showBlogPage(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "blog";
    }

    @GetMapping("/newpost") // Shows the form to create a new post
    public String showNewPostForm() {
        return "newpost";
    }

    @PostMapping("/newpost") // Creates a new post
    public String createNewPost(@RequestParam String title,
                                @RequestParam String content,
                                @RequestParam(required = false) String imagePath) {

        Account currentUser = accountService.findAccountById(1L); // Actual user in phase 1

        Post newPost = new Post(
            System.currentTimeMillis(),
            title,
            content,
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
            imagePath,
            currentUser
        );

        currentUser.addPost(newPost);
        postService.addPost(newPost);

        return "redirect:/blog";
    }
    
}
