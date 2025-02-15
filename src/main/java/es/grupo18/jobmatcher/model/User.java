package es.grupo18.jobmatcher.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    // Se recomienda almacenar la contraseña hasheada
    @Column(nullable = false)
    private String password;
    
    // URL de la foto de perfil
    private String profilePhoto;
    
    private String phone;
    
    private String location;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionnaireResult;
    
    // Se almacena la lista de habilidades (por ejemplo, obtenidas en el cuestionario)
    @ElementCollection
    @CollectionTable(name = "usuario_habilidades", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "habilidad")
    private List<String> skills = new ArrayList<>();
    
    // Se almacena la lista de preferencias (por ejemplo, áreas de interés)
    @ElementCollection
    @CollectionTable(name = "usuario_preferencias", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "preferencia")
    private List<String> preferences = new ArrayList<>();
    
    // Respuestas del cuestionario almacenadas en formato JSON (opcional)
    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionnaireResponses;

    private Integer questionnaireScore;

    public User() {}

    // Constructor básico
    public User(String name, String email, String password, String profilePhoto) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.profilePhoto = profilePhoto;
    }
    
    // Constructor completo
    public User(String name, String email, String password, String profilePhoto,
               String phone, String location, String description, String questionnaireResult) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.profilePhoto = profilePhoto;
        this.phone = phone;
        this.location = location;
        this.description = description;
        this.questionnaireResult = questionnaireResult;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    public String getProfilePhoto() {
        return profilePhoto;
    }
    
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<String> getSkills() {
        return skills;
    }
    
    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
    
    public List<String> getPreferences() {
        return preferences;
    }
    
    public void setPreferences(List<String> preferences) {
        this.preferences = preferences;
    }
    
    public String getQuestionnaireResponses() {
        return questionnaireResponses;
    }
    
    public void setQuestionnaireResponses(String questionnaireResponses) {
        this.questionnaireResponses = questionnaireResponses;
    }
    
    public String getQuestionnaireResult() {
        return questionnaireResult;
    }
    
    public void setQuestionnaireResult(String questionnaireResult) {
        this.questionnaireResult = questionnaireResult;
    }

    public Integer getQuestionnaireScore() {
        return questionnaireScore;
    }

    public void setQuestionnaireScore(Integer score) {
        this.questionnaireScore = score;
    }
}