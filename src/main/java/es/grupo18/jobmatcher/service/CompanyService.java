package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyService {

    private final Company c1;
    private final Company c2;
    private final Company c3;
    private List<Company> companiesList;
    private UserService userService;

    public CompanyService() {

        User user = userService.getUser();

        c1 = new Company(
                02,
                "Microsoft",
                "microsoft@outlook.com",
                "password123",
                "Buscamos programadores experimentados, ofrecemos un ambiente de trabajo flexible y beneficios de bienestar.",
                "California",
                "https://static.vecteezy.com/system/resources/previews/022/100/816/non_2x/microsoft-logo-transparent-free-png.png");

        c2 = new Company(
                12,
                "Google",
                "google@gmail.com",
                "asdfg12345",
                "California",
                "Innovamos en tecnología y buscamos talento apasionado por la inteligencia artificial y la computación en la nube.",
                "https://freelogopng.com/images/all_img/1657952440google-logo-png-transparent.png");

        c3 = new Company(
                22,
                "Apple",
                "apple@email.com",
                "qwerty12345",
                "Silicon Valley",
                "Creamos experiencias revolucionarias con nuestros productos, buscamos diseñadores y desarrolladores visionarios.",
                "https://wallpapers.com/images/hd/black-apple-logo-4k-yn7rtft0sl2x3dhe.jpg");

        c1.addFavouriteUser(user);
        c2.addFavouriteUser(user);
        c3.addFavouriteUser(user);

        companiesList = new ArrayList<>();
        companiesList.add(c1);
        companiesList.add(c2);
        companiesList.add(c3);

        System.out.println("Companies uploaded to memory");

    }

    public Company getCompanyById(Long id) {
        for (Company company : companiesList) {
            if (company.getAccountId() == id) {
                return company;
            }
        }
        return null;
    }

    public List<Company> getCompaniesList() {
        return companiesList;
    }

}
