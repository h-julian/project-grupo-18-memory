package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Noticia;
import es.grupo18.jobmatcher.repository.InMemoryNoticiaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NoticiaController {

    private final InMemoryNoticiaRepository noticiaRepository;

    public NoticiaController(InMemoryNoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Noticia> getNoticia(@PathVariable Long id) {
        return noticiaRepository.findById(id)
                .map(noticia -> ResponseEntity.ok().body(noticia))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping("/")
    public ResponseEntity<Noticia> createNoticia(@RequestBody Noticia noticia) {
        Noticia savedNoticia = noticiaRepository.save(noticia);
        return ResponseEntity.ok(savedNoticia);
    }
}
