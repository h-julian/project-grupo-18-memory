package es.grupo18.jobmatcher.controller;

import es.grupo18.jobmatcher.model.User;
import es.grupo18.jobmatcher.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProfileController {

    private static final Path IMAGES_FOLDER = Paths.get("src/main/resources/static/img/");
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String showProfile(Model model) {
        User user = userService.getUser(); // 🔹 Ahora obtenemos el usuario en memoria desde UserService
        model.addAttribute("user", user);
        model.addAttribute("studies", user.getDegrees());
        model.addAttribute("skills", user.getSkills());
        model.addAttribute("experience", user.getExperience());
        return "profile";
    }

    @PostMapping("/profile/upload_image")
    public String uploadImage(@RequestParam("image") MultipartFile image) throws IOException {
        User user = userService.getUser();
        if (!image.isEmpty()) {
            Files.createDirectories(IMAGES_FOLDER);

            Path imagePath = IMAGES_FOLDER.resolve("profile_" + user.getAccountId() + ".jpg");
            image.transferTo(imagePath);

            // 🔹 Actualizar el usuario en memoria con la nueva imagen
            userService.updateUserImage("/img/profile_" + user.getAccountId() + ".jpg");
        }

        return "redirect:/profile";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model model) {
        model.addAttribute("user", userService.getUser());
        return "profileEditor";
    }

    @PostMapping("/profile/edit")
    public String saveProfile(@RequestParam String name, @RequestParam String email, @RequestParam String phone,
                              @RequestParam String location, @RequestParam String about) {
        userService.updateUserProfile(name, email, phone, location, about);
        return "redirect:/profile";
    }

    @GetMapping("/profile/form")
    public String editProfileInfo(Model model) {
        User user = userService.getUser();
        model.addAttribute("user", user);
        model.addAttribute("studies", String.join(", ", user.getDegrees()));
        model.addAttribute("skills", String.join(", ", user.getSkills()));
        model.addAttribute("experience", user.getExperience());
        return "form";
    }

    @PostMapping("/profile/form")
    public String saveProfileInfo(@RequestParam String studies, @RequestParam String skills,
                                  @RequestParam Integer experience) {
        userService.updateUserDetails(studies, skills, experience);
        return "redirect:/profile";
    }
}
