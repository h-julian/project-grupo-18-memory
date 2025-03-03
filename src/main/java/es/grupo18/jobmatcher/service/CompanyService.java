package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;
import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CompanyService {

    private List<Company> companies;
    private final UserService userService;

    public CompanyService(UserService userService) {
        this.userService = userService;
        this.companies = new ArrayList<>();
        loadInitialCompanies();
    }

    @PostConstruct
    private void loadInitialCompanies() {
        if (!companies.isEmpty()){
            return; // Evita cargar empresas si ya están en memoria
        }

        final Company c1;
        final Company c2;
        final Company c3;
        List<User> favouriteUsersList_c1 = new ArrayList<>();
        List<User> favouriteUsersList_c2 = new ArrayList<>();
        List<User> favouriteUsersList_c3 = new ArrayList<>();

        favouriteUsersList_c1.add(userService.getUser());
        favouriteUsersList_c2.add(userService.getUser());

        c1 = new Company(
                02,
                "Microsoft",
                "microsoft@outlook.com",
                "password123",
                "Buscamos programadores experimentados, ofrecemos un ambiente de trabajo flexible y beneficios de bienestar.",
                "California",
                "https://static.vecteezy.com/system/resources/previews/022/100/816/non_2x/microsoft-logo-transparent-free-png.png",
                favouriteUsersList_c1);

        c2 = new Company(
                12,
                "Google",
                "google@gmail.com",
                "asdfg12345",
                "California",
                "Innovamos en tecnología y buscamos talento apasionado por la inteligencia artificial y la computación en la nube.",
                "https://freelogopng.com/images/all_img/1657952440google-logo-png-transparent.png",
                favouriteUsersList_c2);

        c3 = new Company(
                22,
                "Apple",
                "apple@email.com",
                "qwerty12345",
                "Silicon Valley",
                "Creamos experiencias revolucionarias con nuestros productos, buscamos diseñadores y desarrolladores visionarios.",
                "https://wallpapers.com/images/hd/black-apple-logo-4k-yn7rtft0sl2x3dhe.jpg",
                favouriteUsersList_c3);

        companies.add(c1);
        companies.add(c2);
        companies.add(c3);

        System.out.println("Companies uploaded to memory");
    }

    public List<Company> getCompaniesList() {
        return new ArrayList<>(new HashSet<>(companies));
    }

    public Company getCompanyByName(String name) { // Returns the company with the given name
        return companies.stream()
            .filter(company -> company.getName().equals(name))
            .findFirst()
            .orElse(null);
    }
}