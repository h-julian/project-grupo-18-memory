package es.grupo18.jobmatcher.model;

import java.util.HashMap;
import java.util.Map;

public class Company extends Account {
    private Long id;
    private String bio;
    private String imagePath;
    private Map<String, JobOffer> jobOffersMap;
    private Map<String, User> favoriteUsersMap;
    private int questionnaireScore;
    
    public Company(int id, String name, String email, String password) {
        super(generateNewId(), name, email, password);
        this.questionnaireScore = 0;
        this.jobOffersMap = new HashMap<>();
    }
    
    // Getters
    public Long getId() { return id; }
    public String getBio() { return bio; }
    public String getImagePath() { return imagePath; }
    public Map<String, JobOffer> getJobOffers() { return jobOffersMap; }
    public Map<String, User> getFavoriteUsers() { return favoriteUsersMap; }
    public int getQuestionnaireScore() { return this.questionnaireScore; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBio(String bio) { this.bio = bio; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setJobOffers(Map<String, JobOffer> jobOffersMap) { this.jobOffersMap = jobOffersMap; }
    public void setFavoriteUsers(Map<String, User> favoriteUsersMap) { this.favoriteUsersMap = favoriteUsersMap; }
    public void setQuestionnaireScore(int score) { this.questionnaireScore = score; }

}