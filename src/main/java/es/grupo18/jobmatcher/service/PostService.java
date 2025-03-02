package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Post;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import es.grupo18.jobmatcher.service.PostService;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private final AccountService accountService;

    public PostService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void loadInitialPosts() { // Loads initial posts
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        // Obtains accounts from AccountService
        Account microsoft = accountService.findAccountById(2L);
        Account google = accountService.findAccountById(12L);
        Account apple = accountService.findAccountById(22L);

        // Creates posts directly in memory
        Post post1 = new Post(1L, "Programador - Microsoft",
                "Buscamos programadores experimentados en .NET para un ambiente flexible en California.",
                LocalDateTime.now().minusDays(3).format(dtf), "/img/coder.jpg", microsoft);

        Post post2 = new Post(2L, "Ingeniero IA - Google",
                "Oportunidad para trabajar en inteligencia artificial en California.",
                LocalDateTime.now().minusDays(2).format(dtf), "/img/ai.jpg", google);

        Post post3 = new Post(3L, "Diseñador UI/UX - Apple",
                "Únete al equipo de diseño en Cupertino.",
                LocalDateTime.now().minusDays(1).format(dtf), "/img/cloud_engineer.jpg", apple);

        // Adds all posts to the list
        posts.addAll(List.of(post1, post2, post3));

        // Matches posts with their respective accounts
        microsoft.addPost(post1);
        google.addPost(post2);
        apple.addPost(post3);

        System.out.println("Posts uploaded to memory");
    }

    public List<Post> getAllPosts() { // Returns all posts
        List<Post> reversedPosts = new ArrayList<>(posts);
        Collections.reverse(reversedPosts);
        return reversedPosts;
    }

    public void addPost(Post post) { // Adds a new post
        posts.add(post);
    }

}
