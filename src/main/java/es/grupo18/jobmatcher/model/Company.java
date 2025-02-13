package es.grupo18.jobmatcher.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la empresa
    @Column(nullable = false)
    private String name;

    // Correo electrónico de la empresa
    @Column(nullable = false, unique = true)
    private String email;

    // Contraseña hasheada
    @Column(nullable = false)
    private String password;

    // URL del logo o foto de perfil de la empresa
    private String logoUrl;

    // Respuestas del cuestionario almacenadas en formato JSON
    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionnaireResponses;

    public Company() {
    }

    public Company(String name, String email, String password, String logoUrl) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.logoUrl = logoUrl;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    // No se incluye setter para id, ya que se genera automáticamente

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    public String getPassword() {
        return password;
    }
  
    public void setPassword(String password) {
        this.password = password;
    }
  
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
}
