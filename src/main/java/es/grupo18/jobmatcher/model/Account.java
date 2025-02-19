package es.grupo18.jobmatcher.model;

public abstract class Account {

    private Long accountId;
    private String name;
    private String email;
    private String password;
    
    public Account(Long accountId, String name, String email, String password) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public Long getAccountId() { return accountId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setAccountId(Long accountId) { this.accountId = accountId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

}