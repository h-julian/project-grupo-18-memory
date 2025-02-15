package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserQuestionsController {

    @GetMapping("/user_questions")
    public String user_questions(Model model) {
        model.addAttribute("message", "Bienvenido al cuestionario");
        return "user_questions"; 
    }
}

