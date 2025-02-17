package es.grupo18.jobmatcher.model;

import java.util.Map;

public class User extends Account {
    
    private String phone;
    private String location;
    private String bio;
    private Integer experience;
    private Map<String, String> degreesMap;
    private Map<String, String> skillsMap;
    private String imagePath;
    
    public User(String name, String email, String password, String phone, String location, String bio, Integer experience, Map<String, String> degreesMap, Map<String, String> skillsMap, String imagePath) {
        super(name, email, password);
        this.phone = phone;
        this.location = location;
        this.bio = bio;
        this.experience = experience;
        this.degreesMap = degreesMap;
        this.skillsMap = skillsMap;
        this.imagePath = imagePath;
    }
    
    // Getters
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getBio() { return bio; }
    public Integer getExperience() { return experience; }
    public Map<String, String> getDegrees() { return degreesMap; }
    public Map<String, String> getSkills() { return skillsMap; }
    public String getImagePath() { return imagePath; }

    // Setters
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setBio(String bio) { this.bio = bio; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public void setDegrees(Map<String, String> degreesMap) { this.degreesMap = degreesMap; }
    public void setSkills(Map<String, String> skillsMap) { this.skillsMap = skillsMap; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

}