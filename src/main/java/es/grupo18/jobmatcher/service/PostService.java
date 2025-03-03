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

@Service
public class PostService {

    private final List<Post> posts = new ArrayList<>();
    private final AccountService accountService;

    public PostService(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostConstruct
    public void loadInitialPosts() { // Cargar publicaciones iniciales

        // Obtiene cuentas desde AccountService
        Account microsoft = accountService.findAccountById(2L);
        Account google = accountService.findAccountById(12L);
        Account apple = accountService.findAccountById(22L);

        // Creación de posts directamente en memoria
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        Post post1 = new Post(1L, "Programador - Microsoft",
        "Buscamos programadores experimentados en .NET para un ambiente flexible en California.",
        LocalDateTime.now().minusMonths(1).minusDays(1).minusHours(4).minusMinutes(12).format(dtf), "/img/coder.jpg", microsoft);

        Post post2 = new Post(2L, "Ingeniero IA - Google",
        "Oportunidad para trabajar en inteligencia artificial en California.",
        LocalDateTime.now().minusMonths(0).minusDays(3).minusHours(6).minusMinutes(45).format(dtf), "/img/ai.jpg", google);

        Post post3 = new Post(3L, "Diseñador UI/UX - Apple",
        "Únete al equipo de diseño en Cupertino.",
        LocalDateTime.now().minusMonths(0).minusDays(2).minusHours(1).minusMinutes(1).format(dtf), "/img/cloud_engineer.jpg", apple);

        // Añadir posts a la lista
        posts.addAll(List.of(post1, post2, post3));

        // Asignar posts a sus respectivas cuentas
        if (microsoft != null) microsoft.addPost(post1);
        if (google != null) google.addPost(post2);
        if (apple != null) apple.addPost(post3);

        System.out.println("Posts cargados en memoria");
    }

    public List<Post> getAllPosts() { // Retorna la lista de posts en orden inverso
        List<Post> reversedPosts = new ArrayList<>(posts);
        Collections.reverse(reversedPosts);
        return Collections.unmodifiableList(reversedPosts);
    }

    public void addPost(Post post) { // Agrega un nuevo post
        if (post != null) {
            posts.add(post);
        }
    }
}
