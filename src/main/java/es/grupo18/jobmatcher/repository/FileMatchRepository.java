package es.grupo18.jobmatcher.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.grupo18.jobmatcher.model.Match;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileMatchRepository {
    private final ObjectMapper mapper;
    private final File file = new File("src/main/resources/static/data/matches.json");
    private List<Match> matches;

    public FileMatchRepository() {
        mapper = new ObjectMapper();
        matches = new ArrayList<>();
        loadMatches();
    }

    private void loadMatches() {
        if (file.exists()) {
            try {
                matches = mapper.readValue(file, new TypeReference<List<Match>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                matches = new ArrayList<>();
            }
        }
    }

    private void saveMatches() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            mapper.writeValue(file, matches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Match save(Match match) {
        loadMatches(); // Reload to get latest
        if (match.getId() == null) {
            Long newId = matches.stream()
                    .mapToLong(Match::getId)
                    .max()
                    .orElse(0L) + 1;
            match.setId(newId);
        }
        matches.add(match);
        saveMatches();
        return match;
    }

    public List<Match> findByAccountId(Long userId) {
        loadMatches();
        return matches.stream()
                .filter(m -> m.getAccountId().equals(userId))
                .toList();
    }
}
