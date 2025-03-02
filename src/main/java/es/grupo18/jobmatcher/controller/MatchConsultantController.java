package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.service.MatchService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/match")
public class MatchConsultantController {

    private final MatchService matchService;

    public MatchConsultantController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/consultant")
    @ResponseBody
    public List<Company> getMatchedCompanies() {
        List<Company> companies = matchService.getMatchedCompanies();
        return companies;
    }

    @GetMapping("/consultantPage")
    public String showMatchConsultantPage(Model model) {
        return "matchConsultant";
    }

}
