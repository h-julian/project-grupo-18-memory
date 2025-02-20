package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Match;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.model.Company;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private static final String MATCHES_FILE_PATH = "src/main/resources/static/data/matches.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/companies")
    public List<Company> getMatchedCompanies() {
        User user = User.loadUser();
        if (user == null) {
            return new ArrayList<>();
        }

        List<Match> matches = loadMatches();
        List<Company> matchedCompanies = new ArrayList<>();
        for (Match match : matches) {
            if (match.getId()==user.getAccountId()) {
                Company company = Company.loadCompanyById(match.getCompanyId());
                if (company != null) {
                    matchedCompanies.add(company);
                }
            }
        }
        return matchedCompanies;
    }

    @PostMapping("/like/{companyId}")
    public ResponseEntity<?> likeCompany(@PathVariable Long companyId) {
        User user = User.loadUser();
        if (user == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User not found");
        }

        Match match = new Match(user.getAccountId(), companyId);
        List<Match> matches = loadMatches();
        matches.add(match);
        saveMatches(matches);

        return ResponseEntity.ok().body("{\"success\": true}");
    }

    private List<Match> loadMatches() {
        try {
            return mapper.readValue(new File(MATCHES_FILE_PATH), new TypeReference<List<Match>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveMatches(List<Match> matches) {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(MATCHES_FILE_PATH), matches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
