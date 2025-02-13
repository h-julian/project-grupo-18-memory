package es.grupo18.jobmatcher.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class User {

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
    
    // Tipo de cuenta: "usuario" o "empresa"
    @Column(name = "account_type", nullable = false)
    private String accountType;
    
    // URL de la foto de perfil
    private String profilePhoto;
    
    // Se almacena la lista de habilidades (por ejemplo, obtenidas en el cuestionario)
    @ElementCollection
    @CollectionTable(name = "usuario_habilidades", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "habilidad")
    private List<String> habilidades = new ArrayList<>();
    
    // Se almacena la lista de preferencias (por ejemplo, áreas de interés)
    @ElementCollection
    @CollectionTable(name = "usuario_preferencias", joinColumns = @JoinColumn(name = "usuario_id"))
    @Column(name = "preferencia")
    private List<String> preferencias = new ArrayList<>();
    
    // Respuestas del cuestionario almacenadas en formato JSON (opcional)
    @Lob
    @Column(columnDefinition = "TEXT")
    private String questionnaireResponses;

    public User() {}

    // Constructor completo
    public User(String nombre, String email, String password, String accountType, String profilePhoto) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.accountType = accountType;
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
    
    public String getAccountType() {
        return accountType;
    }
    
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public String getProfilePhoto() {
        return profilePhoto;
    }
    
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    
    public List<String> getHabilidades() {
        return habilidades;
    }
    
    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
    }
    
    public List<String> getPreferencias() {
        return preferencias;
    }
    
    public void setPreferencias(List<String> preferencias) {
        this.preferencias = preferencias;
    }
    
    public String getQuestionnaireResponses() {
        return questionnaireResponses;
    }
    
    public void setQuestionnaireResponses(String questionnaireResponses) {
        this.questionnaireResponses = questionnaireResponses;
    }
}