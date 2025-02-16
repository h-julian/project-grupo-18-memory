package es.grupo18.jobmatcher.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.repository.FileAccountRepository;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private FileAccountRepository accountRepository;

    @PostMapping("/register")  // This should match the fetch URL in the form
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        
        // Validate email not already registered
        if (accountRepository.findByEmail(email) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "El email ya está registrado."));
        }

        // Create temporary account
        Account tempAccount;
        if ("usuario".equalsIgnoreCase(payload.get("account_type"))) {
            User user = new User(
                payload.get("name"),
                email,
                payload.get("password"),
                payload.get("description")
            );
            user.setProfilePhoto(payload.get("profile_photo"));
            tempAccount = user;
        } else if ("empresa".equalsIgnoreCase(payload.get("account_type"))) {
            Company company = new Company(
                payload.get("name"),
                email,
                payload.get("password"),
                payload.get("description")
            );
            company.setProfilePhoto(payload.get("profile_photo"));
            tempAccount = company;
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Tipo de cuenta no válido."));
        }

        // Only store in session, don't save to repository yet
        session.setAttribute("tempAccount", tempAccount);

        return ResponseEntity.ok(Map.of(
            "message", "Registro iniciado",
            "accountType", payload.get("account_type"),
            "name", payload.get("name"),
            "redirect", "/questionnaire"
        ));
    }
}
