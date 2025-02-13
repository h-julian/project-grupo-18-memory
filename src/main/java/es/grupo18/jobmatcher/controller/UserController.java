package es.grupo18.jobmatcher.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
         this.userService = userService;
    }

    // Registro de usuario; se podría adaptar para empresas con otra clase o comprobación
    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario user, HttpSession session){
         Usuario registeredUser = userService.registerUser(user);
         // Se guarda en la sesión, de modo que luego se pueda acceder al perfil
         session.setAttribute("user", registeredUser);
         return registeredUser;
    }

    // Inicio de sesión
    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario loginUser, HttpSession session){
         Usuario user = userService.getUserByEmail(loginUser.getEmail());
         // En un entorno real se agregará validación y encriptación de password
         if(user != null && user.getPassword().equals(loginUser.getPassword())){
             session.setAttribute("user", user);
             return user;
         }
         throw new RuntimeException("Credenciales incorrectas");
    }

    // Obtener datos del perfil desde la sesión
    @GetMapping("/profile")
    public Usuario profile(HttpSession session){
         Usuario user = (Usuario) session.getAttribute("user");
         if(user == null) {
             throw new RuntimeException("No hay usuario logueado");
         }
         return user;
    }

    // Cerrar sesión
    @PostMapping("/logout")
    public String logout(HttpSession session){
         session.invalidate();
         return "Sesión cerrada";
    }
}
