package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.Account;
import es.grupo18.jobmatcher.model.Company;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String showProfile(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("user");
        
        // Debug logs
        System.out.println("Session ID in Profile: " + session.getId());
        System.out.println("User in session: " + (account != null));
        
        if (account == null) {
            System.out.println("No user found in session");
            return "redirect:/login";
        }
        
        System.out.println("Showing profile for: " + account.getName());
        System.out.println("Email: " + account.getEmail());
        
        model.addAttribute("account", account);
        model.addAttribute("accountType", account instanceof Company ? "empresa" : "usuario");
        
        return "profile";
    }
    
    @GetMapping("/profile/edit")
    public String editProfile(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("user");
        if (account != null) {
            model.addAttribute("account", account);
        }
        return "profileEditor"; 
    }
}
