package es.grupo18.jobmatcher.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MatchController {

    @GetMapping("/match")
    public String showMatchPage() {
        return "match";
    }

    @GetMapping("/api/companies")
    @ResponseBody
    public List<Map<String, Object>> getCompanies() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileReader reader = new FileReader("src/main/resources/static/data/companies.json");
        List<Map<String, Object>> companies = mapper.readValue(reader, new TypeReference<List<Map<String, Object>>>() {});

        // Filter only required fields
        return companies.stream()
            .map(company -> Map.of(
                "name", company.getOrDefault("name", ""),
                "location", company.getOrDefault("location", "No especificada"),
                "description", company.getOrDefault("description", "Sin descripción"),
                "profilePhoto", company.getOrDefault("profilePhoto", "/images/default-company.png")
            ))
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