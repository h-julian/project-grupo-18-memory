package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showOptionsPage(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("user");
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "profile";
    }
    
    @GetMapping("/profile/edit")
    public String editProfile(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("user");
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "profileEditor"; // Se asume que existe un template profileEditor.html
    }
}
