package es.grupo18.jobmatcher.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.grupo18.jobmatcher.repository.FileCompanyRepository;
import es.grupo18.jobmatcher.repository.FileUserRepository;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.JobOffer;
import es.grupo18.jobmatcher.model.User;
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
import java.io.IOException;
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
                companyMap.put("id", company.getId());
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

    @PostMapping("/api/match/like/{companyId}")
    @ResponseBody
    public ResponseEntity<?> likeCompany(@PathVariable Long companyId, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "No user logged in"));
        }

        Company company = companyRepository.findById(companyId);
        if (company == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Company not found"));
        }

        // Create a JobOffer object to represent the liked company
        JobOffer jobOffer = new JobOffer(
            company.getId(),
            company.getName(),
            company.getBio()  // Using getBio() instead of getDescription()
        );
        
        // Add to user's favorites
        user.getFavoriteJobOffers().put(String.valueOf(companyId), jobOffer);
        userRepository.save(user);
        
        // Update session
        session.setAttribute("user", user);

        return ResponseEntity.ok(Map.of(
            "message", "Match saved successfully",
            "companyId", companyId
        ));
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