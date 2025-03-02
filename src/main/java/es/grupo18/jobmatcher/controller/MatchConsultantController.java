package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.Match;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/match") 
public class MatchConsultantController {

    private static final String MATCHES_FILE_PATH = "src/main/resources/static/data/matches.json";
    private static final String COMPANIES_FILE_PATH = "src/main/resources/static/data/companies.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    @GetMapping("/consultant") // Shows the consultant page
    @ResponseBody
    public List<Company> getMatchedCompanies() { 
        List<Match> matches = loadMatches();
        List<Long> matchedCompanyIds = matches.stream()
                .map(Match::getCompanyId)
                .collect(Collectors.toList());

        return loadCompanies().stream()
                .filter(company -> matchedCompanyIds.contains(company.getAccountId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/consultantPage") // Shows the consultant page
    public String showMatchConsultantPage(Model model) {
        return "matchConsultant";
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

}
