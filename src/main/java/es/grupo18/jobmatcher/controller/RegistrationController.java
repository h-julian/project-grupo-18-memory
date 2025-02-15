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

    // Registro: recibe los datos del formulario (nombre, email, password, account_type)
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload, HttpSession session) {
        String name = payload.get("name");
        String email = payload.get("email");
        String password = payload.get("password");
        String accountType = payload.get("account_type");

        // Validate email not already registered
        if (accountRepository.findByEmail(email) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "El email ya está registrado."));
        }

        // Create temporary account object and store in session
        Account tempAccount;
        if ("usuario".equalsIgnoreCase(accountType)) {
            tempAccount = new User(name, email, password, "");
        } else if ("empresa".equalsIgnoreCase(accountType)) {
            tempAccount = new Company(name, email, password, "");
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Tipo de cuenta no válido."));
        }

        // Store temporary account in session instead of saving to repository
        session.setAttribute("tempAccount", tempAccount);
        
        return ResponseEntity.ok(Map.of(
            "message", "Datos validados correctamente",
            "account_type", accountType,
            "name", name,
            "email", email
        ));
    }

    // Inicio de sesión: verifica email y password
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        String password = payload.get("password");

        Account account = accountRepository.findByEmail(email);
        if (account == null || !account.getPassword().equals(password)) {
            return ResponseEntity.badRequest().body("Credenciales incorrectas.");
        }

        session.setAttribute("user", account);
        return ResponseEntity.ok(account);
    }
}
