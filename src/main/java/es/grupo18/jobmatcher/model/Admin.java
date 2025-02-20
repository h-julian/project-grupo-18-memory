package es.grupo18.jobmatcher.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends Account {

    private String roleDetail; // Por ejemplo: "SUPER_ADMIN", "MODERATOR", etc.
    
    public Admin() {}
    
    public Admin(String name, String email, String password, String roleDetail) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.roleDetail = roleDetail;
    }
    
    // Getters y setters

    public String getRoleDetail() {
        return roleDetail;
    }
  
    public void setRoleDetail(String roleDetail) {
        this.roleDetail = roleDetail;
    }
}
