package es.grupo18.jobmatcher.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.grupo18.jobmatcher.model.Post;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BlogService {

    private final List<Post> posts = new ArrayList<>();
    private final UserService userService;
    private static final String JSON_FILE_PATH = "src/main/resources/static/data/posts.json";

    public BlogService(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void loadPostsFromJson() {
        File file = Paths.get(JSON_FILE_PATH).toFile();
        if (!file.exists()) {
            System.out.println("⚠️ No se encontró el archivo JSON, iniciando con lista vacía.");
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            posts.addAll(objectMapper.readValue(file, new TypeReference<List<Post>>() {}));
            System.out.println("✅ Posts cargados desde JSON: " + posts.size());
        } catch (IOException e) {
            System.err.println("❌ Error cargando posts desde JSON: " + e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        List<Post> reversedPosts = new ArrayList<>(posts);
        Collections.reverse(reversedPosts);
        return reversedPosts;
    }

    public void addPost(String title, String content, String imagePath) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        long newId = posts.isEmpty() ? 1 : posts.get(posts.size() - 1).getPostId() + 1;
        String ownerName = userService.getCurrentUserName();
        posts.add(new Post(newId, title, content, timestamp, imagePath, ownerName));
        System.out.println("✅ Nuevo post agregado en memoria.");
    }
}

