package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }

    @GetMapping("/options")
    public String showOptionsPage() {
        return "options";
    }

    @GetMapping("/userquestions")
    public String showUserQuestions(){
        return "user_questions";
    }
}
