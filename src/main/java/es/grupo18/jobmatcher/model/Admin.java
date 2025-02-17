package es.grupo18.jobmatcher.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends Account {
    
    private String roleDetail;
    
    public Admin() {}
    
    public Admin(String name, String email, String password, String roleDetail) {
        super(name, email, password);
        this.roleDetail = roleDetail;
    }
    
    public String getRoleDetail() { return roleDetail; }
}