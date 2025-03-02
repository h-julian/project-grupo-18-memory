package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Post;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {

    private final PostService postService;

    public BlogService(PostService postService) {
        this.postService = postService;
    }

    public List<Post> getAllPosts() { // Returns all posts
        return postService.getAllPosts();
    }

    public void addPost(Post post) { // Adds a new post
        postService.addPost(post);
    }

}
