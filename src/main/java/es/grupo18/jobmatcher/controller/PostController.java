package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PostController {

    private static final String POSTS_FILE_PATH = "src/main/resources/static/data/posts.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/posts")
    @ResponseBody
    public ResponseEntity<?> createPost(@RequestBody Post post) {
        List<Post> posts = loadPosts();

        // Formats the timestamp

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        post.setTimestamp(dateFormat.format(new Date()));

        posts.add(post);
        savePosts(posts);
        return ResponseEntity.ok().body("{\"success\": true}");
    }

    private List<Post> loadPosts() {
        try {
            return mapper.readValue(new File(POSTS_FILE_PATH), new TypeReference<List<Post>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void savePosts(List<Post> posts) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(POSTS_FILE_PATH), posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}