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
            List<Company> currentCompanies = new ArrayList<>(companies); // Crear una copia de la lista actual
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, currentCompanies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Genera un id secuencial, comenzando en 0
    private Long generateNewId() {
        if (companies.isEmpty()) {
            return 0L;
        } else {
            return companies.stream()
                    .map(Company::getId)
                    .filter(id -> id != null)
                    .max(Comparator.naturalOrder())
                    .orElse(-1L) + 1;
        }
    }

    public Company save(Company company) {
        loadCompanies(); // Recargar empresas existentes
        if (company.getId() == null) {
            Long newId = companies.stream()
                .mapToLong(c -> c.getId() != null ? c.getId() : 0L)
                .max()
                .orElse(-1L) + 1;
            company.setId(newId);
        }
        companies.add(company); // Añadir la nueva empresa
        saveCompanies(); // Guardar toda la lista
        return company;
    }

    public Company findByEmail(String email) {
        return companies.stream()
            .filter(c -> c.getEmail().equalsIgnoreCase(email))
            .findFirst()
            .orElse(null);
    }

    public List<Company> findAll() {
        // Puedes recargar desde disco si lo necesitas, pero en este caso se asume que la lista en memoria es la actual
        return companies;
    }
}
