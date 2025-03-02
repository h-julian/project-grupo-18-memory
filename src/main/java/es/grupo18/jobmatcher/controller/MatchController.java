package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.Match;
import es.grupo18.jobmatcher.service.UserService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {
    private static final String MATCHES_FILE_PATH = "src/main/resources/static/data/matches.json";
    private static final String COMPANIES_FILE_PATH = "src/main/resources/static/data/companies.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private UserService userService;

    @GetMapping("/companies")
    @ResponseBody
    public List<Company> getMatchedCompanies() { // Gets all companies that have been matched
        return loadCompanies();
    }

    @PostMapping("/like/{companyId}") 
    public ResponseEntity<?> likeCompany(@PathVariable Long companyId) { // User can like or dislike a company
        List<Match> matches = loadMatches();
        boolean matchExists = matches.removeIf(match -> match.getCompanyId().equals(companyId));

        if (!matchExists) {
            Match match = new Match(null, companyId);
            matches.add(match);
        }

        saveMatches(matches);
        userService.addFavouriteCompanyById(companyId);

        return ResponseEntity.ok().body("{\"success\": true, \"liked\": " + !matchExists + "}");
    }

    @GetMapping("")
    public String showMatchPage(Model model) { // Shows the match page
        List<Company> companies = loadCompanies();
        model.addAttribute("companies", companies);
        return "match";
    }

    private List<Match> loadMatches() { // Loads all matches from JSON
        try {
            return mapper.readValue(new File(MATCHES_FILE_PATH), new TypeReference<List<Match>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
 
    private List<Company> loadCompanies() { // Loads all companies from JSON
        try {
            return mapper.readValue(new File(COMPANIES_FILE_PATH), new TypeReference<List<Company>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveMatches(List<Match> matches) { // Saves all matches to JSON
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(MATCHES_FILE_PATH), matches);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}