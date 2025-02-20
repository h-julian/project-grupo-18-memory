package es.grupo18.jobmatcher.repository;
import es.grupo18.jobmatcher.model.Post;

public class PostRepository {
    public Post findById(Long id) {
        Post post = new Post();
        return post;
    }
    public Post save(Post post) {
        return post;
    }
}
