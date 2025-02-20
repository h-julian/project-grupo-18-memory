package es.grupo18.jobmatcher.model;

import java.util.HashMap;
import java.util.Map;



public class Company extends Account {  
    private String bio;
    private String location;
    private String imagePath;
    private Map<String, JobOffer> jobOffersMap;
    private Map<String, User> favoriteUsersMap;
    private int questionnaireScore;

    // Constructors

    public Company(){

    }

    public Company(long accountId, String name, String email, String password, String location, String bio, String imagePath, Map<String, JobOffer> jobOffersMap, Map<String, User> favoriteUsersMap) {
        super(accountId, name, email, password);
        this.location = location;
        this.bio = bio;
        this.imagePath = imagePath;
        this.jobOffersMap = (jobOffersMap != null) ? jobOffersMap : new HashMap<>();
        this.favoriteUsersMap = (favoriteUsersMap != null) ? favoriteUsersMap : new HashMap<>();
    }

    public Company(long accountId, String name, String email, String password, String bio, String imagePath) {
        super(accountId, name, email, password);
        this.bio = bio;
        this.imagePath = imagePath;
        this.jobOffersMap = new HashMap<>();
        this.favoriteUsersMap = new HashMap<>();
        this.questionnaireScore = 0;
        this.location = "";
    }
    
    // Getters
    public String getBio() { return bio; }
    public String getImagePath() { return imagePath; }
    public Map<String, JobOffer> getJobOffers() { return jobOffersMap; }
    public Map<String, User> getFavoriteUsers() { return favoriteUsersMap; }
    public int getQuestionnaireScore() { return this.questionnaireScore; }
    public String getLocation() { return location; }

    // Setters
    public void setBio(String bio) { this.bio = bio; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setJobOffers(Map<String, JobOffer> jobOffersMap) { this.jobOffersMap = jobOffersMap; }
    public void setFavoriteUsers(Map<String, User> favoriteUsersMap) { this.favoriteUsersMap = favoriteUsersMap; }
    public void setQuestionnaireScore(int score) { this.questionnaireScore = score; }

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

    public void addImage(String imagePath) {
    this.imagePath = imagePath;
}

    public void removeImage() {
        this.imagePath = null;
    }


    // toString
    @Override
    public String toString() {
        return "Company{" + super.toString() +
                "bio='" + bio  +
                ", location='" + location + 
                ", imagePath='" + imagePath + 
                ", jobOffersMap=" + jobOffersMap +
                ", favoriteUsersMap=" + favoriteUsersMap +
                ", questionnaireScore=" + questionnaireScore +
                '}';
    }


}