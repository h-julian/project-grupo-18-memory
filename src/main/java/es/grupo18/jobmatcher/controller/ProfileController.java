package es.grupo18.jobmatcher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.grupo18.jobmatcher.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfileController {

    private static final Path IMAGES_FOLDER = Paths.get("src/main/resources/static/img/");

    // Hardcoded user
    private static final User user = new User(
            1L,
            "Juan Javier",
            "juja@gmail.com",
            "password123",
            "651479899",
            "Carballo, A Coruña",
            "Y tal",
            5,
            Arrays.asList("Grado en Ingeniería Informática", "Máster en Ciberseguridad"),
            Arrays.asList("Java", "Spring Boot", "SQL", "Pentesting"),
            "/img/profile.jpg");

    @GetMapping("/profile")
    public String showProfile(Model model) {
        model.addAttribute("user", user);
        model.addAttribute("studies", user.getDegrees());
        model.addAttribute("skills", user.getSkills());
        model.addAttribute("experience", user.getExperience());
        return "profile";
    }

    @PostMapping("/profile/upload_image")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        if (!image.isEmpty()) {
            // Checks if the image folder exists.
            Files.createDirectories(IMAGES_FOLDER);

            // Saves the user's profile image
            Path imagePath = IMAGES_FOLDER.resolve("profile_" + user.getAccountId() + ".jpg");
            image.transferTo(imagePath);

            // Updates the path with the user's profile image
            user.setImagePath("/img/profile_" + user.getAccountId() + ".jpg");
        }

        return "redirect:/profile"; // Redirects to profile to check the changes
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", user); // Uses the hardcoded user by default
        return "profileEditor";
    }

    @PostMapping("/profile/edit")
    public String saveProfile(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
            @RequestParam String location, @RequestParam String about) {
        // Updates the user's information in local memory
        user.setName(name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLocation(location);
        user.setBio(about);

        return "redirect:/profile";
    }

    @GetMapping("/profile/form")
    public String editProfileInfo(Model model) {
        model.addAttribute("user", user);
        model.addAttribute("studies", String.join(", ", user.getDegrees()));
        model.addAttribute("skills", String.join(", ", user.getSkills()));
        model.addAttribute("experience", user.getExperience());
        return "form";
    }

    @PostMapping("/profile/form")
    public String saveProfileInfo(@RequestParam String studies, @RequestParam String skills,
            @RequestParam Integer experience) {
        // Splits texts by commas
        List<String> updatedStudies = Arrays.asList(studies.split(",\\s*"));
        List<String> updatedSkills = Arrays.asList(skills.split(",\\s*"));

        // Updates local memory
        user.setDegrees(updatedStudies);
        user.setSkills(updatedSkills);
        user.setExperience(experience);

        return "redirect:/profile";
    }

}
