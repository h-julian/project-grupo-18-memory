package es.grupo18.jobmatcher.model;

public class Match {

    private User user;
    private Company company;
    private String timestamp;

    public Match() {
    }

    public Match(User user, Company company) {
        this.user = user;
        this.company = company;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    // Getters

    public User getUser() {
        return user;
    }

    public Company getCompany() {
        return company;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Setters

    public void setUser(User user) {
        this.user = user;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
