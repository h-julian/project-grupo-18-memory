package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Post;
import es.grupo18.jobmatcher.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("")
    public String showBlogPage(Model model) {
        List<Post> posts = blogService.getAllPosts();
        System.out.println("📢 Mostrando " + posts.size() + " posts en el blog.");
        model.addAttribute("posts", posts);
        return "blog";
    }

    @GetMapping("/newpost")
    public String showNewPostForm() {
        return "newpost";
    }

    @PostMapping("/newpost")
    public String createNewPost(@RequestParam String title,
                                @RequestParam String content,
                                @RequestParam(required = false) String imagePath,
                                @RequestParam long ownerId,
                                @RequestParam String ownerType) {
        blogService.addPost(title, content, imagePath, ownerId, ownerType);
        return "redirect:/blog";
    }
}
