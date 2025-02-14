package es.grupo18.jobmatcher.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class OptionsController {
    @GetMapping("/options")
    public String option() {
        return "options";
    }
}
