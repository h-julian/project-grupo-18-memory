package es.grupo18.jobmatcher.model;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company extends Account {

    private String logoUrl;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionnaireResponses;

    private Integer questionnaireScore;
    
    public Company() {}
    
    public Company(String name, String email, String password, String logoUrl) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.logoUrl = logoUrl;
    }
    
    // Getters y setters

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

    public Integer getQuestionnaireScore() {
        return questionnaireScore;
    }

    public void setQuestionnaireScore(Integer score) {
        this.questionnaireScore = score;
    }
}