package es.grupo18.jobmatcher.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class User extends Account {
    
    private String phone;
    private String location;
    private String bio;
    private Integer experience;
    private List<String> degreesList;
    private List<String> skillsList;
    private String imagePath;
    private Integer questionnaireScore;
    private List<Integer> matchId;

    private static final String FILE_PATH = "src/main/resources/static/data/users.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public User() {
        
    }
    
    public User(Long accountId, String name, String email, String password, String phone, String location, String bio, Integer experience, List<String> degreesList, List<String> skillsList, String imagePath) {
        super(accountId, name, email, password);
        this.bio = bio;
        this.phone = phone;
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
        this.questionnaireScore = 0;
        this.experience = 0;
    }
    
    // Getters
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getBio() { return bio; }
    public Integer getExperience() { return experience; }
    public List<String> getDegrees() { return degreesList; }
    public List<String> getSkills() { return skillsList; }
    public String getImagePath() { return imagePath; }
    public Integer getQuestionnaireScore() { return questionnaireScore; }
    public List<Integer> getMatchId() { return matchId; }

    // Setters
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setBio(String bio) { this.bio = bio; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public void setDegrees(List<String> degreesList) { this.degreesList = degreesList; }
    public void setSkills(List<String> skillsList) { this.skillsList = skillsList; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setQuestionnaireScore(Integer questionnaireScore) { this.questionnaireScore = questionnaireScore; }

    // Update methods

    public void updatePhone(String newPhone){
        this.phone = newPhone;
    }

    public void updateLocation(String newLocation){
        this.location = newLocation;
    }

    public void updateBio(String newBio){
        this.bio = newBio;
    }

    public void updateExperience(Integer newExperience){
        this.experience = newExperience;
    }

    public void updateDegrees(List<String> newDegrees){
        this.degreesList = newDegrees;
    }

    public void updateSkills(List<String> newSkills){
        this.skillsList = newSkills;
    }

    public void updateImagePath(String newImagePath){
        this.imagePath = newImagePath;
    }  

    // Add and remove methods

    public void addImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void removeImage() {
        this.imagePath = null;
    }

    public void addLikedCompany(int id) {
        this.matchId.add(id);
    }

    // Static methods to load and save user data from/to JSON file

    public static User loadUser() {
        try {
            List<User> users = mapper.readValue(new File(FILE_PATH), new TypeReference<List<User>>() {});
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
    
}