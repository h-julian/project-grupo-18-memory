package es.grupo18.jobmatcher.model;

import java.util.HashMap;
import java.util.Map;

public class Company extends Account {
    
    private String location;
    private String bio;
    private String imagePath;
    private Map<String, JobOffer> jobOffersMap;
    private Map<String, User> favoriteUsersMap;
    
    public Company(Long accountId, String name, String email, String password, String location, String bio, String imagePath, Map<String, JobOffer> jobOffersMap, Map<String, User> favoriteUsersMap) {
        super(accountId, name, email, password);
        this.location = location;
        this.bio = bio;
        this.imagePath = imagePath;
        this.jobOffersMap = new HashMap<>();
        this.favoriteUsersMap = new HashMap<>();

    }
    
    // Getters
    public String getLocation() { return location; }
    public String getBio() { return bio; }
    public String getImagePath() { return imagePath; }
    public Map<String, JobOffer> getJobOffers() { return jobOffersMap; }
    public Map<String, User> getFavoriteUsers() { return favoriteUsersMap; }

    // Setters
    public void setLocation(String location) { this.location = location; }
    public void setBio(String bio) { this.bio = bio; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setJobOffers(Map<String, JobOffer> jobOffersMap) { this.jobOffersMap = jobOffersMap; }
    public void setFavoriteUsers(Map<String, User> favoriteUsersMap) { this.favoriteUsersMap = favoriteUsersMap; }

}