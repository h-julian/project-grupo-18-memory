package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NewsRepository {
    private final Map<Long, Post> posts = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/static/data/posts.json");
            Post[] postsArray = mapper.readValue(is, Post[].class);
            for (Post post : postsArray) {
                post.setPostId(idCounter.getAndIncrement());
                posts.put(post.getPostId(), post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getPostId() == null) {
            post.setPostId(idCounter.getAndIncrement());
        }
        posts.put(post.getPostId(), post);
        return post;
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }
}
