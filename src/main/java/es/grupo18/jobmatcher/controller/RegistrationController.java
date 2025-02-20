package es.grupo18.jobmatcher.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;
import es.grupo18.jobmatcher.model.JobOffer;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.repository.FileAccountRepository;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class RegistrationController {

    @Autowired
    private FileAccountRepository accountRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> payload, HttpSession session) {
        String email = payload.get("email");
        String name = payload.get("name");
        String password = payload.get("password");
        String accountType = payload.get("account_type");
        String phone = payload.get("phone");
        String location = payload.get("location");
        String bio = payload.get("bio");
        Integer experience = Integer.parseInt(payload.get("experience"));
        List<String> degreesList = List.of(payload.get("degrees").split(",")); // Split by comma
        List<String> skillsList = List.of(payload.get("skills").split(",")); // Split by comma
        Map<String, JobOffer> favoriteJobOffersMap = Map.of();
        String imagePath = payload.get("image_path");
        int questionnaireScore = 0;
        Long id = System.currentTimeMillis();


        // Validar que los campos requeridos no sean nulos
        if (email == null || name == null || password == null || accountType == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Faltan datos obligatorios."));
        }

        // Verificar si el email ya está registrado
        if (accountRepository.findByEmail(email) != null) {
            return ResponseEntity.badRequest().body(Map.of("error", "El email ya está registrado."));
        }

        // Crear la cuenta según el tipo de usuario
        Account tempAccount;
        if ("usuario".equalsIgnoreCase(accountType)) {
            tempAccount = new User(id, name, email, password, phone, location, bio, experience, degreesList, skillsList, imagePath);
        } else if ("empresa".equalsIgnoreCase(accountType)) {
            tempAccount = new Company(1,name,email,password);
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Tipo de cuenta no válido."));
        }

        // Guardar en la sesión temporalmente antes de ser almacenado en la base de datos
        session.setAttribute("tempAccount", tempAccount);

        return ResponseEntity.ok(Map.of(
            "message", "Registro iniciado",
            "accountType", accountType,
            "name", name,
            "redirect", "/questionnaire"
        ));
    }
}
