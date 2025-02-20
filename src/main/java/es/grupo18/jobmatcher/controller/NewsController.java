package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.repository.NewsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsRepository newsRepository;

    public NewsController(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getNews(@PathVariable Long id) {
         return newsRepository.findById(id)
                .map(news -> ResponseEntity.ok().body(news))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/")
    public ResponseEntity<Post> createNews(@RequestBody Post news) {
        Post savedNews = newsRepository.save(news);
        return ResponseEntity.ok(savedNews);
    }
}