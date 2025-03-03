package es.grupo18.jobmatcher.model;

import java.time.LocalDateTime;

public class Post {

    private static long idCounter = 1; // Contador de IDs para generar IDs únicos
    private long postId;
    private String title;
    private String content;
    private LocalDateTime timestamp;
    private String imagePath;
    private Account owner;

    // Constructor vacío con valores predeterminados
    public Post() {
        this.postId = generateNewPostId();
        this.title = "";
        this.content = "";
        this.timestamp = LocalDateTime.now();
        this.imagePath = null;
        this.owner = null;
    }

    // Completo sin id, generada automaticamente
    public Post(String title, String content, LocalDateTime timestamp, String imagePath, Account owner) {
        this(generateNewPostId(), title, content, timestamp, imagePath, owner);
    }

    // Constructor completo
    public Post(long postId, String title, String content, LocalDateTime timestamp, String imagePath, Account owner) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now();
        this.imagePath = imagePath;
        this.owner = owner;
    }

    // Generación automática de IDs
    private static synchronized long generateNewPostId() {
        return idCounter++;
    }

    // Getters
    public long getPostId() { return postId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getImagePath() { return imagePath; }
    public Account getOwner() { return owner; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(LocalDateTime timestamp) { 
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now(); 
    }    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setOwner(Account owner) { this.owner = owner; }

    // Métodos de imagen
    public void removeImage() { this.imagePath = null; }
}
