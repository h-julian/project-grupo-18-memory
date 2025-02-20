package es.grupo18.jobmatcher.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.grupo18.jobmatcher.repository.FileCompanyRepository;
import es.grupo18.jobmatcher.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    // Debug endpoint to check raw data
    @GetMapping("/api/companies/debug")
    @ResponseBody
    public List<Map<String, Object>> getRawCompanies() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileReader reader = new FileReader("src/main/resources/static/data/companies.json");
        return mapper.readValue(reader, new TypeReference<List<Map<String, Object>>>() {});
    }
}