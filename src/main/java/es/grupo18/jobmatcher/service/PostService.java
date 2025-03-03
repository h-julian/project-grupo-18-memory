package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Post;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private final AccountService accountService;

    public PostService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void loadInitialPosts() { // Uploads initial posts

        // Obtains accounts from the account service

        Account microsoft = accountService.findAccountById(2L);
        Account google = accountService.findAccountById(12L);
        Account apple = accountService.findAccountById(22L);

        // Creates posts directly in memory

        Post post1 = new Post("Programador - Microsoft",
                "Buscamos programadores experimentados en .NET para un ambiente flexible en California.",
                LocalDateTime.now().minusDays(3), "/img/coder.jpg", microsoft);

        Post post2 = new Post("Ingeniero IA - Google",
                "Oportunidad para trabajar en inteligencia artificial en California.",
                LocalDateTime.now().minusDays(2), "/img/ai.jpg", google);

        Post post3 = new Post("Diseñador UI/UX - Apple",
                "Únete al equipo de diseño en Cupertino.",
                LocalDateTime.now().minusDays(1), "/img/uiux.jpg", apple);

        // Adds posts to the list

        posts.addAll(List.of(post1, post2, post3));

        // Sets posts to their respective accounts

        if (microsoft != null) microsoft.addPost(post1);
        if (google != null) google.addPost(post2);
        if (apple != null) apple.addPost(post3);

        System.out.println("Posts cargados en memoria");
    }

    public List<Post> getAllPosts() { // Returns the posts list in reverse order
        List<Post> reversedPosts = new ArrayList<>(posts);
        Collections.reverse(reversedPosts);
        return Collections.unmodifiableList(reversedPosts);
    }

    public void addPost(Post post) { // Adds a new post
        if (post != null) {
            posts.add(post);
        }
    }
    
}
