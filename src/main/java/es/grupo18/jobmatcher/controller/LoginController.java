package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.service.UserService;

@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @PostMapping("/login")
    public String processLogin(@RequestParam String email, 
                             @RequestParam String password,
                             RedirectAttributes redirectAttributes) {
        
        User user = userService.getUser();
        
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            // Login exitoso, redirigir a la página principal
            return "redirect:/main";
        } else {
            // Login fallido, mostrar mensaje de error
            redirectAttributes.addFlashAttribute("error", "Email o contraseña incorrectos");
            return "redirect:/login";
        }
    }
}