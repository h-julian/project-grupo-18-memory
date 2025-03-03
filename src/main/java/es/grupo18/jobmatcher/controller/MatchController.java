package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.service.CompanyService;
import es.grupo18.jobmatcher.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {

    private final CompanyService companyService;
    private final UserService userService;

    public MatchController(CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showMatchPage(Model model) {
        List<Company> companies = companyService.getCompaniesList();
        User currentUser = userService.getUser();

        // Crear listas separadas
        List<Company> favouriteCompanies = currentUser.getFavouriteCompanies();
        List<Company> nonFavouriteCompanies = new ArrayList<>();

        for (Company company : companies) {
            if (!favouriteCompanies.contains(company)) {
                nonFavouriteCompanies.add(company);
            }
        }

        model.addAttribute("favouriteCompanies", favouriteCompanies);
        model.addAttribute("nonFavouriteCompanies", nonFavouriteCompanies);

        return "match";
    }


    @PostMapping("/addFavourite")
    public String addFavourite(@RequestParam String companyName) {
        User currentUser = userService.getUser();
        Company company = companyService.getCompanyByName(companyName);

        if (company != null && !currentUser.getFavouriteCompanies().contains(company)) {
            currentUser.addFavouriteCompany(company);
        }

        return "redirect:/match"; // Recargar la vista para reflejar cambios
    }

    @PostMapping("/removeFavourite")
    public String removeFavourite(@RequestParam String companyName, @RequestParam String origin) {
        User currentUser = userService.getUser();
        Company company = companyService.getCompanyByName(companyName);

        if (company != null) {
            currentUser.removeFavouriteCompany(company);
        }

        return "redirect:" + origin;    }

    @GetMapping("/consultant")
    public String showConsultantMatchPage(Model model) {
        User currentUser = userService.getUser();
        List<Company> favouriteCompanies = currentUser.getFavouriteCompanies();
        List<Company> mutualMatchCompanies = new ArrayList<>();
    
        // Filtrar empresas que también tengan al usuario en su lista de favoritos
        for (Company company : favouriteCompanies) {
            if (company.getFavouriteUsers().contains(currentUser)) {
                mutualMatchCompanies.add(company);
            }
        }
    
        model.addAttribute("mutualMatchCompanies", mutualMatchCompanies);
    
        return "matchConsultant";
    }
    
}
