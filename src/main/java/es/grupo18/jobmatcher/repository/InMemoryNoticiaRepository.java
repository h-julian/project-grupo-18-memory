package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.Noticia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryNoticiaRepository {

    private final Map<Long, Noticia> noticias = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    @PostConstruct
    public void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/noticias.json");
            Noticia[] noticiasArray = mapper.readValue(is, Noticia[].class);
            for (Noticia noticia : noticiasArray) {
                noticia.setId(idCounter.getAndIncrement());
                noticias.put(noticia.getId(), noticia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Noticia> findById(Long id) {
        return Optional.ofNullable(noticias.get(id));
    }

    public Noticia save(Noticia noticia) {
        if(noticia.getId() == null) {
            noticia.setId(idCounter.getAndIncrement());
        }
        noticias.put(noticia.getId(), noticia);
        return noticia;
    }

    public List<Noticia> findAll() {
        return new ArrayList<>(noticias.values());
    }
}
