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
    
<<<<<<< HEAD
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

    public User() {}

    // Constructor completo
    public User(String name, String email, String password, String profilePhoto) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.profilePhoto = profilePhoto;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    // No se incluye setter para id ya que se genera automáticamente

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
=======
    public User(Long id, String name, String email, String password, String phone, String location, String bio, Integer experience, List<String> degreesList, List<String> skillsList, String imagePath) {
        super(id, name, email, password);
        this.bio = bio;
        this.favoriteJobOffersMap = new HashMap<>();
        this.imagePath = imagePath;
>>>>>>> parent of 6f75eab (Merge branch 'main' of https://github.com/DWS-2025/project-grupo-18)
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
}