package es.grupo18.jobmatcher.model;

public class Admin extends Account {
    
    private String role;
    
    public Admin(String name, String email, String password, String role) {
        super(name, email, password);
        this.role = role;
    }
    
    public String getRole() { return role; }
    
    public void setRole(String role) { this.role = role; }

}