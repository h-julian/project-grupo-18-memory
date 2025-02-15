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
        
        // Get temporary account from session
        Account tempAccount = (Account) session.getAttribute("tempAccount");
        if (tempAccount == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                               .body(Map.of("error", "Sesión no válida"));
        }

        // Calculate score
        boolean isCompany = "empresa".equals(type);
        int score = scoreCalculator.calculateScore(answers, isCompany);

        // Save account with score
        if (isCompany && tempAccount instanceof Company) {
            Company company = (Company) tempAccount;
            company.setQuestionnaireScore(score);
            companyRepository.save(company);
        } else if (!isCompany && tempAccount instanceof User) {
            User user = (User) tempAccount;
            user.setQuestionnaireScore(score);
            userRepository.save(user);
        } else {
            return ResponseEntity.badRequest()
                               .body(Map.of("error", "Tipo de cuenta no coincide"));
        }

        // Clear temporary account from session
        session.removeAttribute("tempAccount");
        // Set the actual account in session
        session.setAttribute("user", tempAccount);

        return ResponseEntity.ok(Map.of(
            "score", score,
            "message", "Registro completado exitosamente"
            "redirect", "/main"
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

