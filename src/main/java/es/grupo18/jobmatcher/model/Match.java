package es.grupo18.jobmatcher.model;

public class Match {

    private User user;
    private Company company;
    private String timestamp;

    public Match() {}

    public Match(User user, Company company) {
        this.user = user;
        this.company = company;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    // Getters and Setters

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}