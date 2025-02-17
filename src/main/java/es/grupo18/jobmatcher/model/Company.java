package es.grupo18.jobmatcher.model;

import java.util.HashMap;
import java.util.Map;

public class Company extends Account {
    
    private String location;
    private String bio;
    private String imagePath;
    private Map<String, JobOffer> jobOffersMap;
    
    public Company(String name, String email, String password, String location, String bio, String imagePath) {
        super(name, email, password);
        this.location = location;
        this.bio = bio;
        this.jobOffersMap = new HashMap<>();
        this.imagePath = imagePath;
    }
    
    // Getters
    public String getLocation() { return location; }
    public String getBio() { return bio; }
    public Map<String, JobOffer> getJobOffers() { return jobOffersMap; }
    public String getImagePath() { return imagePath; }

    // Setters
    public void setLocation(String location) { this.location = location; }
    public void setBio(String bio) { this.bio = bio; }
    public void setJobOffers(Map<String, JobOffer> jobOffersMap) { this.jobOffersMap = jobOffersMap; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

}