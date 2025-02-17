package es.grupo18.jobmatcher.model;

import java.util.List;

public class User extends Account {
    
    private String phone;
    private String location;
    private String bio;
    private Integer experience;
    private List<String> degreesList;
    private List<String> skillsList;
    private String imagePath;
    
    public User() {}
    
    public User(String name, String email, String password, String phone, String location, String bio, Integer experience,  List<String> degreesList, List<String> skillsList, String imagePath) {
        super(name, email, password);
        this.phone = phone;
        this.location = location;
        this.bio = bio;
        this.experience = experience;
        this.degreesList = degreesList;
        this.skillsList = skillsList;
        this.imagePath = imagePath;
    }
    
    // Getters
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getBio() { return bio; }
    public Integer getExperience() { return experience; }
    public List<String> getDegrees() { return degreesList; }
    public List<String> getSkills() { return skillsList; }
    public String getImagePath() { return imagePath; }

    // Setters
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setBio(String bio) { this.bio = bio; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public void setDegrees(List<String> degreesList) { this.degreesList = degreesList; }
    public void setSkills(List<String> skillsList) { this.skillsList = skillsList; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

}