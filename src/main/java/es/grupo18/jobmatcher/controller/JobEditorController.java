package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobEditorController {
    @GetMapping("/jobEditor")
    public String jorEditor() {
        return "jobEditor";
    }
}
