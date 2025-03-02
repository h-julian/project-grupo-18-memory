package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.dto.CompanyDTO;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.service.CompanyService;
import es.grupo18.jobmatcher.service.MatchService;
import es.grupo18.jobmatcher.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final CompanyService companyService;
    private final UserService userService;

    public MatchController(MatchService matchService, CompanyService companyService, UserService userService) {
        this.matchService = matchService;
        this.companyService = companyService;
        this.userService = userService;
    }

    @GetMapping("")
    public String showMatchPage() {
        return "match"; // Solo devuelve la plantilla HTML
    }
    
    @GetMapping("/companies")
    @ResponseBody
    public List<CompanyDTO> getCompanies() {
        List<Company> companies = companyService.getCompaniesList();
        System.out.println("Companies loaded: " + companies.size());
        
        // Convertir Companies a CompanyDTOs
        List<CompanyDTO> companyDTOs = new ArrayList<>();
        for (Company company : companies) {
            companyDTOs.add(new CompanyDTO(company));
        }
        
        return companyDTOs;
    }

    @PostMapping("/like")
    public ResponseEntity<?> likeCompany(@RequestParam String companyName) {
        Company company = companyService.getCompanyByName(companyName);
        User user = userService.getUser();

        if (company == null || user == null) {
            return ResponseEntity.badRequest().body("{\"success\": false, \"message\": \"Invalid company or user.\"}");
        }

        boolean matchExists = matchService.toggleMatch(user, company);

        return ResponseEntity.ok().body("{\"success\": true, \"liked\": " + matchExists + "}");
    }
}