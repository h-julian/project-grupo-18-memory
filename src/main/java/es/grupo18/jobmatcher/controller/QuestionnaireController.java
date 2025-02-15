package es.grupo18.jobmatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
public class QuestionnaireController {

    @Autowired
    private FileUserRepository userRepository;

    @Autowired
    private FileCompanyRepository companyRepository;

    @PostMapping("/api/saveQuestionnaireResult")
    public ResponseEntity<?> saveResult(@RequestBody Map<String, Object> result, HttpSession session) {
        Account account = (Account) session.getAttribute("user");
        if (account instanceof User) {
            User user = (User) account;
            // Guardar el resultado como propiedad del usuario
            user.setQuestionnaireScore(Integer.parseInt(result.get("score").toString()));
            userRepository.save(user);
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body("Usuario no encontrado");
    }

    @GetMapping("/company-questionnaire")
    public String showCompanyQuestionnaire() {
        return "company-questionnaire";
    }

    @PostMapping("/api/company-questionnaire")
    @ResponseBody
    public ResponseEntity<?> saveCompanyAnswers(@RequestBody Map<String, Integer> answers, 
                                              HttpSession session) {
        Account account = (Account) session.getAttribute("user");
        if (account instanceof Company) {
            Company company = (Company) account;
            // Calcular puntuación basada en las respuestas
            int score = calculateScore(answers);
            company.setQuestionnaireScore(score);
            // Guardar resultados
            companyRepository.save(company);
            return ResponseEntity.ok().body(Map.of("score", score));
        }
        return ResponseEntity.badRequest().body("Usuario no válido");
    }

    private int calculateScore(Map<String, Integer> answers) {
        // Implementar lógica de puntuación según tus necesidades
        return answers.values().stream().mapToInt(Integer::intValue).sum();
    }

    @GetMapping("/questionnaire")
    public String showQuestionnaire(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("user");
        if (account != null) {
            // Determine account type and add attributes
            String accountType = (account instanceof Company) ? "empresa" : "usuario";
            model.addAttribute("accountType", accountType);
            model.addAttribute("userName", account.getName());
            
            // Debug information
            System.out.println("Account type: " + accountType);
            System.out.println("User name: " + account.getName());
            
            return "form";
        }
        return "redirect:/login";
    }
}

