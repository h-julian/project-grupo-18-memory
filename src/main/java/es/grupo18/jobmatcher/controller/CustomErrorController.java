package es.grupo18.jobmatcher.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        int statusCode = (status != null) ? Integer.parseInt(status.toString()) : 500;
        model.addAttribute("status", statusCode);

        if (statusCode == HttpStatus.NOT_FOUND.value()) {
            model.addAttribute("error", "Página no encontrada");
        } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            model.addAttribute("error", "Error interno del servidor");
        } else {
            model.addAttribute("error", "Error inesperado");
        }

        return "error";
    }

    public String getErrorPath() {
        return "/error";
    }
}
