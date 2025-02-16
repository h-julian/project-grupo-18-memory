package es.grupo18.jobmatcher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import es.grupo18.jobmatcher.repository.FileAccountRepository;
import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;

import java.util.Map;

@Controller
public class AuthController {

    @Autowired
    private FileAccountRepository accountRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/api/login")
    @ResponseBody
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        String password = payload.get("password");

        System.out.println("Login attempt for: " + email); // Debug log

        Account account = accountRepository.findByEmail(email);
        
        if (account != null && account.getPassword().equals(password)) {
            session.setAttribute("user", account);
            
            return ResponseEntity.ok(Map.of(
                "message", "Login exitoso",
                "accountType", account instanceof Company ? "empresa" : "usuario",
                "name", account.getName(),
                "email", account.getEmail(),
                "redirect", "/main"
            ));
        }

        return ResponseEntity.badRequest().body(Map.of(
            "error", "Credenciales inválidas"
        ));
    }

    @PostMapping("/api/logout")
    @ResponseBody
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of(
            "message", "Sesión cerrada exitosamente",
            "redirect", "/login"
        ));
    }

    @GetMapping("/check-session")
    @ResponseBody
    public ResponseEntity<?> checkSession(HttpSession session) {
        Account account = (Account) session.getAttribute("user");
        if (account != null) {
            return ResponseEntity.ok(Map.of(
                "loggedIn", true,
                "accountType", account instanceof Company ? "empresa" : "usuario",
                "name", account.getName()
            ));
        }
        return ResponseEntity.ok(Map.of("loggedIn", false));
    }

    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }
}
