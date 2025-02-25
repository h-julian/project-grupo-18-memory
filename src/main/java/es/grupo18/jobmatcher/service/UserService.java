package es.grupo18.jobmatcher.service;

import es.grupo18.jobmatcher.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Arrays;

@Service
public class UserService {

    private final User user; // Usuario en memoria

    public UserService() {
        // 🔹 Simulación de usuario en memoria
        this.user = new User(
            1L, 
            "Juan Javier", // Nombre inicial del usuario en sesión
            "juja@gmail.com", 
            "password123",
            "651479899", 
            "Carballo, A Coruña", 
            "Y tal",
            5, 
            List.of("Grado en Ingeniería Informática", "Máster en Ciberseguridad"),
            List.of("Java", "Spring Boot", "SQL", "Pentesting"),
            "/img/profile.jpg"
        );
    }

    // 🔹 Devuelve el usuario en memoria
    public User getUser() {
        return user;
    }

    // 🔹 Devuelve el nombre actual del usuario
    public String getCurrentUserName() {
        return user.getName();
    }

    // 🔹 Permite actualizar el perfil del usuario
    public void updateUserProfile(String name, String email, String phone, String location, String about) {
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLocation(location);
        user.setBio(about);
    }

    // 🔹 Permite actualizar la imagen del usuario
    public void updateUserImage(String imagePath) {
        user.setImagePath(imagePath);
    }

    // 🔹 Permite actualizar estudios, habilidades y experiencia
    public void updateUserDetails(String studies, String skills, Integer experience) {
        user.setDegrees(Arrays.asList(studies.split(",\\s*")));
        user.setSkills(Arrays.asList(skills.split(",\\s*")));
        user.setExperience(experience);
    }
}
