package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Company;
import org.springframework.ui.Model;
import es.grupo18.jobmatcher.service.CompanyService;
import es.grupo18.jobmatcher.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {

    private final CompanyService companyService;
    private final UserService userService;

    public MatchController( CompanyService companyService, UserService userService) {
        this.companyService = companyService;
        this.userService = userService;
    }
    
    @GetMapping("")
    public String showMatchPage(Model model) {
        List<Company> companies = companyService.getCompaniesList();
        model.addAttribute("companies", companies);
        return "match"; // Retorna la plantilla match.mustache
    }

    @GetMapping("/consultant")
    public String showMatchConsultant(Model model) {
        List<Company> companies = companyService.getCompaniesList();
        model.addAttribute("companies", companies);
        return "matchConsultant";
    }

    @PostMapping("/toggleFavourite")
    public ResponseEntity<String> toggleFavouriteCompany(@RequestParam String companyName) {
        Company company = companyService.getCompanyByName(companyName);
        if (company != null) {
            boolean isFavourite = userService.toggleFavouriteCompany(company);
            return ResponseEntity.ok(isFavourite ? "Añadido a favoritos" : "Eliminado de favoritos");
        }
        return ResponseEntity.badRequest().body("Empresa no encontrada");
    }

}
