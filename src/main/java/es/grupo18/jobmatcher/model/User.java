package es.grupo18.jobmatcher.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Account {
    
    private String phone;
    private String location;
    private String bio;
    private Integer experience;
    private List<String> degreesList;
    private List<String> skillsList;
    private Map<String, JobOffer> favoriteJobOffersMap;
    private String imagePath;

    public User() {
        
    }
    
    public User(Long id, String name, String email, String password, String phone, String location, String bio, Integer experience, List<String> degreesList, List<String> skillsList, String imagePath) {
        super(id, name, email, password);
        this.bio = bio;
        this.phone = phone;
        this.location = location;
        this.experience = experience;
        this.degreesList = degreesList;
        this.skillsList = skillsList;
        this.favoriteJobOffersMap = (favoriteJobOffersMap != null) ? favoriteJobOffersMap : new HashMap<>();
        this.imagePath = imagePath;
    }
    
    // Getters
    public String getPhone() { return phone; }
    public String getLocation() { return location; }
    public String getBio() { return bio; }
    public Integer getExperience() { return experience; }
    public List<String> getDegrees() { return degreesList; }
    public List<String> getSkills() { return skillsList; }
    public Map<String, JobOffer> getFavoriteJobOffers() { return favoriteJobOffersMap; }
    public String getImagePath() { return imagePath; }

    // Setters
    public void setPhone(String phone) { this.phone = phone; }
    public void setLocation(String location) { this.location = location; }
    public void setBio(String bio) { this.bio = bio; }
    public void setExperience(Integer experience) { this.experience = experience; }
    public void setDegrees(List<String> degreesList) { this.degreesList = degreesList; }
    public void setSkills(List<String> skillsList) { this.skillsList = skillsList; }
    public void setFavoriteJobOffers(Map<String, JobOffer> favoriteJobOffersMap) { this.favoriteJobOffersMap = favoriteJobOffersMap; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

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

}