package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main") // Shows the main page
    public String showMainPage() {
        return "main";
    }

    @GetMapping("/options") // Shows the options page, we decided not to create a dedicated Controller because of its simplicity
    public String showOptionsPage() {
        return "options";
    }

}
