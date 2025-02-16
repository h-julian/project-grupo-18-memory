package es.grupo18.jobmatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.repository.FileUserRepository;
import es.grupo18.jobmatcher.repository.FileCompanyRepository;
import es.grupo18.jobmatcher.service.ScoreCalculator;

@Controller
public class QuestionnaireController {

    @Autowired
    private FileUserRepository userRepository;
    
    @Autowired
    private FileCompanyRepository companyRepository;
    
    @Autowired
    private ScoreCalculator scoreCalculator;

    @PostMapping("/api/{type}-questionnaire")
    @ResponseBody
    public ResponseEntity<?> saveQuestionnaireResults(
            @PathVariable String type,
            @RequestBody Map<String, Integer> answers,
            HttpSession session) {
        
        Account account = (Account) session.getAttribute("tempAccount");
        if (account == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                               .body(Map.of("error", "No hay registro pendiente"));
        }

        // Calculate score using scoreCalculator service
        int score = scoreCalculator.calculateScore(answers, account instanceof Company);
        
        // Set score and save account
        if (account instanceof Company) {
            Company company = (Company) account;
            company.setQuestionnaireScore(score);
            companyRepository.save(company);
        } else {
            User user = (User) account;
            user.setQuestionnaireScore(score);
            userRepository.save(user);
        }
        
        // Update session with saved account
        session.removeAttribute("tempAccount");
        session.setAttribute("user", account);

        return ResponseEntity.ok(Map.of(
            "message", "Registro completado",
            "score", score,
            "redirect", "/profile"
        ));
    }

    @GetMapping("/company-questionnaire")
    public String showCompanyQuestionnaire() {
        return "company-questionnaire";
    }

    @GetMapping("/questionnaire")
    public String showQuestionnaire(Model model, HttpSession session) {
        // Check for tempAccount instead of user during registration flow
        Account account = (Account) session.getAttribute("tempAccount");
        if (account == null) {
            // Also check for regular user session as fallback
            account = (Account) session.getAttribute("user");
            if (account == null) {
                return "redirect:/login";
            }
        }
        
        // Determine account type and add attributes
        String accountType = (account instanceof Company) ? "empresa" : "usuario";
        model.addAttribute("accountType", accountType);
        model.addAttribute("userName", account.getName());
        
        // Debug information
        System.out.println("Account type: " + accountType);
        System.out.println("User name: " + account.getName());
        System.out.println("Session attribute used: " + 
            (session.getAttribute("tempAccount") != null ? "tempAccount" : "user"));
        
        return "form";
    }
}

