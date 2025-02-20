package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.post;
import es.grupo18.jobmatcher.repository.InMemorypostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class postController {

    private final InMemorypostRepository postRepository;

    public postController(InMemorypostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<post> getpost(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> ResponseEntity.ok().body(post))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/")
    public ResponseEntity<post> createpost(@RequestBody post post) {
        post savedpost = postRepository.save(post);
        return ResponseEntity.ok(savedpost);
    }
}
