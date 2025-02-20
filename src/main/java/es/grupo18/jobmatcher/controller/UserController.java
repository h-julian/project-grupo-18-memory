package es.grupo18.jobmatcher.controller;

import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;
import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.service.UserService;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    
    public UserController(UserService userService){
         this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user, HttpSession session){
          User registeredUser = userService.registerUser(user);
         // Se guarda en la sesión, de modo que luego se pueda acceder al perfil
         session.setAttribute("user", registeredUser);
         return registeredUser;
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser, HttpSession session){
         User user = userService.getUserByEmail(loginUser.getEmail());
         if(user != null && user.getPassword().equals(loginUser.getPassword())){
             session.setAttribute("user", user);
             return user;
         }
         throw new RuntimeException("Credenciales incorrectas");
    }

    // Obtener datos del perfil desde la sesión
    @GetMapping("/profile")
    public User profile(HttpSession session){
         User user = (User) session.getAttribute("user");
         if(user == null) {
             throw new RuntimeException("No hay usuario logueado");
         }
         return user;
    }

    // Cerrar sesión: remueve el atributo "user" sin invalidar la sesión completa
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.removeAttribute("user");
        return ResponseEntity.ok().build();
    }

}
