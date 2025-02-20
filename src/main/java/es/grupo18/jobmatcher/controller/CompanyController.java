package es.grupo18.jobmatcher.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
public class CompanyController {

    @GetMapping("/match/companies")
    public List<Map<String, Object>> getCompanies() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FileReader reader = new FileReader("src/main/resources/static/data/companies.json");
        List<Map<String, Object>> companies = mapper.readValue(reader, new TypeReference<List<Map<String, Object>>>() {});
        return companies;
    }
}