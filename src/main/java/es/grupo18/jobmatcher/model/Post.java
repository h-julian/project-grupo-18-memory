package es.grupo18.jobmatcher.model;

import java.time.LocalDateTime;

public class Post {

    private static long idCounter = 1; // ID counter to generate unique IDs
    private long postId;
    private String title;
    private String content;
    private LocalDateTime timestamp;
    private String imagePath;
    private Account owner;

    // Empty constructor

    public Post() {
        this.postId = generateNewPostId();
        this.title = "";
        this.content = "";
        this.timestamp = LocalDateTime.now();
        this.imagePath = null;
        this.owner = null;
    }

    // Complete constructor, but without ID

    public Post(String title, String content, LocalDateTime timestamp, String imagePath, Account owner) {
        this(generateNewPostId(), title, content, timestamp, imagePath, owner);
    }

    // Complete constructor

    public Post(long postId, String title, String content, LocalDateTime timestamp, String imagePath, Account owner) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now();
        this.imagePath = imagePath;
        this.owner = owner;
    }

    // Automathic ID generator

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
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = (timestamp != null) ? timestamp : LocalDateTime.now(); }    
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setOwner(Account owner) { this.owner = owner; }

    // Image method

    public void removeImage() { this.imagePath = null; }
    
}
