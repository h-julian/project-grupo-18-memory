package es.grupo18.jobmatcher.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company extends Account {  
    private String bio;
    private String location;
    private String imagePath;
    private Map<String, JobOffer> jobOffersMap;
    private Map<String, User> favoriteUsersMap;
    private int questionnaireScore;

    private static final String FILE_PATH = "src/main/resources/static/data/companies.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Constructors

    public Company() {}

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

    public Company(Long accountId, String name, String email, String password, String description, String location, String profilePhoto) {
        super(accountId, name, email, password);
        this.bio = description;
        this.location = location;
        this.imagePath = profilePhoto;
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
    public void setLocation(String location) { this.location = location; }
    public void setProfilePhoto(String profilePhoto) { this.imagePath = profilePhoto; }

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

    public static Company loadCompanyById(Long companyId) {
        try {
            List<Company> companies = mapper.readValue(new File(FILE_PATH), new TypeReference<List<Company>>() {});
            for (Company company : companies) {
                if (company.getAccountId() == companyId) {
                    return company;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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