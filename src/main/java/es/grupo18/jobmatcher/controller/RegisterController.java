package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping
    public String registerUser(@ModelAttribute User user, Model model) {
        // Lógica para registrar la cuenta en la base de datos
        userRepository.save(user);

        // Redirigir al cuestionario correspondiente después del registro
        if ("empresa".equals(user.getAccountType())) {
            return "redirect:/company_questions.html";
        } else {
            return "redirect:/user_questions.html";
        }
    }
}
