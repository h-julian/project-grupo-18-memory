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
        this.jobOffersMap = (jobOffersMap != null) ? jobOffersMap : new HashMap<>();
        this.favoriteUsersMap = (favoriteUsersMap != null) ? favoriteUsersMap : new HashMap<>();


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

    // Update methods
    public void updateLocation(String newLocation){
        this.location = newLocation;
    }

    public void updateBio(String newBio){
        this.bio = newBio;
    }

    public void updateImagePath(String newImagePath){
        this.imagePath = newImagePath;
    }

    //Add & Remove methods
    public void addJobOffer(JobOffer jobOffer){
        this.jobOffersMap.put(String.valueOf(jobOffer.getOfferId()), jobOffer);
    }

    public void removeJobOffer(JobOffer jobOffer){
        this.jobOffersMap.remove(String.valueOf(jobOffer.getOfferId()));
    }

    public void addFavoriteUser(User user){
        this.favoriteUsersMap.put(String.valueOf(user.getAccountId()), user);
    }

    public void removeFavoriteUser(User user){
        this.favoriteUsersMap.remove(String.valueOf(user.getAccountId()));
    }

    // toString
    @Override
    public String toString() {
        return "Company{id=" + getAccountId() + ", name=" + getName() + ", location=" + location + ", bio=" + bio + "}";
    }


}