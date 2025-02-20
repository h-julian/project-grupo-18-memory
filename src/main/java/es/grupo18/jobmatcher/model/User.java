package es.grupo18.jobmatcher.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.ArrayList;

public class User extends Account {

    private String phone;
    private String location;
    private String bio;
    private Integer experience;
    private List<String> degreesList;
    private List<String> skillsList;
    private String imagePath;
<<<<<<< Updated upstream
    private List<Integer> matchId;
=======
>>>>>>> Stashed changes

    private static final String FILE_PATH = "src/main/resources/static/data/users.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public User() {

    }

    public User(Long accountId, String name, String email, String password, String phone, String location, String bio, Integer experience, List<String> degreesList, List<String> skillsList, String imagePath) {
        super(accountId, name, email, password);
        this.phone = phone;
        this.bio = bio;
        this.location = location;
        this.experience = experience;
        this.degreesList = degreesList;
        this.skillsList = skillsList;
        this.imagePath = imagePath;
        this.matchId = new ArrayList<>();
    }

    public User(Long accountId, String name, String email, String password, String bio, String imagePath) {
        super(accountId, name, email, password);
        this.bio = bio;
        this.imagePath = imagePath;
        this.skillsList = new ArrayList<>();
        this.degreesList = new ArrayList<>();
        this.experience = 0;
    }

    // Getters
    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getBio() {
        return bio;
    }

    public Integer getExperience() {
        return experience;
    }

    public List<String> getDegrees() {
        return degreesList;
    }

    public List<String> getSkills() {
        return skillsList;
    }

    public String getImagePath() {
        return imagePath;
    }

    // Setters
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setDegrees(List<String> degreesList) {
        this.degreesList = degreesList;
    }

    public void setSkills(List<String> skillsList) {
        this.skillsList = skillsList;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    // Update methods

    public void updatePhone(String newPhone) {
        this.phone = newPhone;
    }

    public void updateLocation(String newLocation) {
        this.location = newLocation;
    }

    public void updateBio(String newBio) {
        this.bio = newBio;
    }

    public void updateExperience(Integer newExperience) {
        this.experience = newExperience;
    }

    public void updateDegrees(List<String> newDegrees) {
        this.degreesList = newDegrees;
    }

    public void updateSkills(List<String> newSkills) {
        this.skillsList = newSkills;
    }

    public void updateImagePath(String newImagePath) {
        this.imagePath = newImagePath;
    }

    // Add and remove methods

    public void addImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void removeImage() {
        this.imagePath = null;
    }

    // Static methods to load and save user data from/to JSON file

    public static User loadUser() {
        try {
            List<User> users = mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {
            });
            if (!users.isEmpty()) {
                return users.get(0); // Return the first user
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveUser(User user) {
        try {
            List<User> users = new ArrayList<>();
            users.add(user);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadId() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Map<String, Object>> matches = objectMapper.readValue(
                    Paths.get("src/main/resources/matches.json").toFile(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });
            this.matchId = matches.stream()
                    .map(match -> (Integer) match.get("id"))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            this.matchId = List.of();
        }
    }

}