package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Match;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.model.Company;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/match")
public class MatchController {

    private static final String MATCHES_FILE_PATH = "src/main/resources/static/data/matches.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/companies")
    public String getMatchedCompanies(Model model) {
        User user = User.loadUser();
        if (user == null) {
            model.addAttribute("companies", new ArrayList<>());
            return "companies";
        }

        List<Match> matches = loadMatches();
        List<Company> matchedCompanies = new ArrayList<>();
        for (Match match : matches) {
            if (match.getId() == user.getAccountId()) {
                Company company = Company.loadCompanyById(match.getCompanyId());
                if (company != null) {
                    matchedCompanies.add(company);
                }
            }
        }
        model.addAttribute("companies", matchedCompanies);
        return "companies";
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

    @GetMapping("/match")
    public String showMatchPage() {
        return "match";
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