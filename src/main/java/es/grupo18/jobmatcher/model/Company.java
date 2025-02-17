package es.grupo18.jobmatcher.model;

import jakarta.persistence.*;

@Entity
@Table(name = "companies")
public class Company extends Account {
    
    private String logoUrl;
    
    public Company() {}
    
    public Company(String name, String email, String password, String logoUrl) {
        super(name, email, password);
        this.logoUrl = logoUrl;
    }
    
    public String getLogoUrl() { return logoUrl; }
}