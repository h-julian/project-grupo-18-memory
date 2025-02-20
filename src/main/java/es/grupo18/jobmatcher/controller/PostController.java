package es.grupo18.jobmatcher.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import es.grupo18.jobmatcher.repository.PostRepository;
import es.grupo18.jobmatcher.model.Post;

@RestController
@RequestMapping("/api/news")
public class PostController {

    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /*
    @GetMapping("/{id}")
    public ResponseEntity<Post> getpost(@PathVariable Long id) {
        return postRepository.findById(id).map(post -> ResponseEntity.ok().body(post)).orElse(ResponseEntity.notFound().build());
    } 
    */
    
    @PostMapping("/")
    public ResponseEntity<Post> createpost(@RequestBody Post post) {
        Post savedpost = postRepository.save(post);
        return ResponseEntity.ok(savedpost);
    }
}
