package es.grupo18.jobmatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class OptionsController {
    @GetMapping("/options")
    public String showOptionsPage(Model model) {
        return "options";
    }
    @GetMapping("/notification")
    public String showNotificationPage(Model model) {
        return "notification";
    }

}
