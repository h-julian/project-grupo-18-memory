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
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BlogService {

    private final List<Post> posts = new ArrayList<>();
    private final AtomicLong postCounter = new AtomicLong(1);
    private static final String JSON_FILE_PATH = "src/main/resources/static/data/posts.json"; // Ruta absoluta

    @PostConstruct
    public void loadPostsFromJson() {
        try {
            File file = Paths.get(JSON_FILE_PATH).toFile();

            if (!file.exists()) {
                System.out.println("⚠️ No se encontró el archivo JSON en " + JSON_FILE_PATH + ", iniciando con lista vacía.");
                return;
            }

            ObjectMapper objectMapper = new ObjectMapper();
            List<Post> loadedPosts = objectMapper.readValue(file, new TypeReference<List<Post>>() {});
            posts.addAll(loadedPosts);

            if (!posts.isEmpty()) {
                long maxId = posts.stream().mapToLong(Post::getPostId).max().orElse(0);
                postCounter.set(maxId + 1);
            }

            System.out.println("✅ Posts cargados desde JSON: " + posts.size());
        } catch (IOException e) {
            System.err.println("❌ Error cargando posts desde JSON: " + e.getMessage());
        }
    }

    public List<Post> getAllPosts() {
        return new ArrayList<>(posts);
    }

    public void addPost(String title, String content, String imagePath, long ownerId, String ownerType) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Post newPost = new Post(postCounter.getAndIncrement(), title, content, timestamp, imagePath, ownerId, ownerType);
        posts.add(newPost);
        System.out.println("Nuevo post agregado en memoria: " + newPost);
    }
}
