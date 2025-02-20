package es.grupo18.jobmatcher.model;

import java.util.Date;

public class Post {
    
    private Long id; 
    private String title;
    private String content;
    private Date timestamp; 
    private String imagePath;
    private long ownerId;
    private String ownerType;

    // Constructor
    public Post() {}

    public Post(long id, String title, String content, Date timestamp, String imagePath, long ownerId, String ownerType) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.imagePath = imagePath;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getTimestamp() { return timestamp; }
    public String getImagePath() { return imagePath; }
    public long getOwnerId() { return ownerId; }
    public String getOwnerType() { return ownerType; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }
    public void setOwnerType(String ownerType) { this.ownerType = ownerType; }    
}

