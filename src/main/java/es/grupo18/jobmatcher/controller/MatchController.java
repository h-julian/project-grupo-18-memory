package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.Match;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    private static final String COMPANIES_FILE_PATH = "src/main/resources/static/data/companies.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/companies")
    @ResponseBody
    public List<Company> getMatchedCompanies() {
        return loadCompanies();
    }

    @PostMapping("/like/{companyId}")
    public ResponseEntity<?> likeCompany(@PathVariable Long companyId) {
        List<Match> matches = loadMatches();
        boolean matchExists = matches.removeIf(match -> match.getCompanyId().equals(companyId));

        if (!matchExists) {
            Match match = new Match(null, companyId); // No user ID, only company ID
            matches.add(match);
        }

        saveMatches(matches);

        return ResponseEntity.ok().body("{\"success\": true, \"liked\": " + !matchExists + "}");
    }

    @GetMapping("/match")
    public String showMatchPage(Model model) {
        List<Company> companies = loadCompanies();
        model.addAttribute("companies", companies);
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

    private List<Company> loadCompanies() {
        try {
            return mapper.readValue(new File(COMPANIES_FILE_PATH), new TypeReference<List<Company>>() {});
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