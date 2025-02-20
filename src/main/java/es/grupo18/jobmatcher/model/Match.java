package es.grupo18.jobmatcher.model;

public class Match {
    private Long id;
    private Long userId;
    private Long companyId;
    private String timestamp;

    public Match() {}

    public Match(Long userId, Long companyId) {
        this.userId = userId;
        this.companyId = companyId;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getCompanyId() { return companyId; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
