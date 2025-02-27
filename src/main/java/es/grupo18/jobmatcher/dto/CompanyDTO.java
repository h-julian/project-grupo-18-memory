package es.grupo18.jobmatcher.dto;
import es.grupo18.jobmatcher.model.Company;

public class CompanyDTO {
    private long accountId;
    private String name;
    private String email;
    private String bio;
    private String location;
    private String imagePath;
    
    // Constructor vacío necesario para Jackson
    public CompanyDTO() {}
    
    // Constructor para crear desde Company
    public CompanyDTO(Company company) {
        this.accountId = company.getAccountId();
        this.name = company.getName();
        this.email = company.getEmail();
        this.bio = company.getBio();
        this.location = company.getLocation();
        this.imagePath = company.getImagePath();
    }
    
    // Getters y setters
    public long getAccountId() { return accountId; }
    public void setAccountId(long accountId) { this.accountId = accountId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}