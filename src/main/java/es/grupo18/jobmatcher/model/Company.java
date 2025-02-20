package es.grupo18.jobmatcher.model;

<<<<<<< HEAD
import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company extends Account {

    private String logoUrl;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionnaireResponses;
    
    public Company() {}
    
    public Company(String name, String email, String password, String logoUrl) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.logoUrl = logoUrl;
=======
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
>>>>>>> parent of 6f75eab (Merge branch 'main' of https://github.com/DWS-2025/project-grupo-18)
    }
    
    // Getters y setters

<<<<<<< HEAD
    public String getLogoUrl() {
        return logoUrl;
    }
  
    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
  
    public String getQuestionnaireResponses() {
        return questionnaireResponses;
    }
  
    public void setQuestionnaireResponses(String questionnaireResponses) {
        this.questionnaireResponses = questionnaireResponses;
    }
}
=======
    // Setters
    public void setId(Long id) { this.id = id; }
    public void setBio(String bio) { this.bio = bio; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setJobOffers(Map<String, JobOffer> jobOffersMap) { this.jobOffersMap = jobOffersMap; }
    public void setFavoriteUsers(Map<String, User> favoriteUsersMap) { this.favoriteUsersMap = favoriteUsersMap; }
    public void setQuestionnaireScore(int score) { this.questionnaireScore = score; }

}
>>>>>>> parent of 6f75eab (Merge branch 'main' of https://github.com/DWS-2025/project-grupo-18)
