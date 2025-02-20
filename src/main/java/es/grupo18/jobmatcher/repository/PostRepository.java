package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.Post;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PostRepository {
    public Optional<Post> findById(Long id) {
        Post post = new Post();
        return Optional.ofNullable(post);
    }

    public Post save(Post post) {
        return post;
    }
}
