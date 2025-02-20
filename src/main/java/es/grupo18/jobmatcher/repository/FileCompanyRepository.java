package es.grupo18.jobmatcher.repository;

import es.grupo18.jobmatcher.model.Company;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class FileCompanyRepository {

    private final ObjectMapper mapper;
    private final File file = new File("src/main/resources/static/data/companies.json");
    private List<Company> companies;

    public FileCompanyRepository() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        companies = new ArrayList<>();
        loadCompanies();
    }

    private void loadCompanies() {
        if (file.exists()) {
            try {
                List<Company> loadedCompanies = mapper.readValue(file, new TypeReference<List<Company>>() {});
                if (loadedCompanies != null) {
                    companies = loadedCompanies;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveCompanies() {
        try {
            file.getParentFile().mkdirs();
            List<Company> currentCompanies = new ArrayList<>(companies); 
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, currentCompanies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Long generateNewId() {
        if (companies.isEmpty()) {
            return 0L;
        } else {
            return companies.stream()
                    .map(Company::getAccountId)
                    .filter(id -> id != null)
                    .max(Comparator.naturalOrder())
                    .orElse(-1L) + 1;
        }
    }

    public Company save(Company company) {
        loadCompanies(); 
        companies.add(company);
        saveCompanies();
        return company;
    }

    public Company findByEmail(String email) {
        return companies.stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    public Company findById(long accountId) {
        return companies.stream()
            .filter(c -> c.getAccountId() == accountId)
            .findFirst()
            .orElse(null);
    }

    public List<Company> findAll() {
        return companies;
    }
}
