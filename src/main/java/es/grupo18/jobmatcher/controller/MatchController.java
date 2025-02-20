<<<<<<< Updated upstream
package es.grupo18.jobmatcher.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.grupo18.jobmatcher.repository.FileCompanyRepository;
import es.grupo18.jobmatcher.repository.FileUserRepository;
import es.grupo18.jobmatcher.repository.FileMatchRepository;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MatchController {

    @Autowired
    private FileCompanyRepository companyRepository;

    @Autowired
    private FileUserRepository userRepository;

    @Autowired
    private FileMatchRepository matchRepository;

    @GetMapping("/match")
    public String showMatchPage() {
        return "match";
    }

    @GetMapping("/api/match/companies")
    @ResponseBody
    public List<Map<String, Object>> getCompanies() {
        List<Company> companies = companyRepository.findAll();
        
        // Debug log
        System.out.println("Found " + companies.size() + " companies");
        
        return companies.stream()
            .map(company -> {
                Map<String, Object> companyMap = new HashMap<>();
                companyMap.put("accountId", company.getAccountId());
                companyMap.put("name", company.getName());
                companyMap.put("location", company.getLocation());
                companyMap.put("description", company.getBio());
                companyMap.put("profilePhoto", company.getImagePath());
                
                // Debug log
                System.out.println("Processing company: " + company.getName());
                
                return companyMap;
            })
            .collect(Collectors.toList());
    }

    @PostMapping("/api/match/like/{accountId}")
    @ResponseBody
    public ResponseEntity<?> likeCompany(@PathVariable String accountId, HttpSession session) {
        try {
            User user = (User) session.getAttribute("user");
            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "error", "Usuario no autenticado"));
            }

            // Create and save match
            Match match = new Match(user.getAccountId(), Long.parseLong(accountId));
            matchRepository.save(match);

            // Update user's liked companies list
            User.saveUser(user);

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Match guardado correctamente",
                "matchId", match.getId()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("success", false, "error", e.getMessage()));
        }
    }

    // Debug endpoint to check raw data
    @GetMapping("/api/companies/debug")
    @ResponseBody
    public List<Map<String, Object>> getRawCompanies() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileReader reader = new FileReader("src/main/resources/static/data/companies.json");
        return mapper.readValue(reader, new TypeReference<List<Map<String, Object>>>() {});
    }
}
=======
>>>>>>> Stashed changes
