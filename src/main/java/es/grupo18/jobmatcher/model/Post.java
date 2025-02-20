package es.grupo18.jobmatcher.model;

import java.util.Date;

public class Post {
    
    private long postId; 
    private String title;
    private String content;
    private Date timestamp; 
    private String imagePath;
    private long ownerId;
    private String ownerType;

    // Constructor
    public Post() {}

    public Post(long postId, String title, String content, Date timestamp, String imagePath, long ownerId, String ownerType) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.timestamp = (timestamp != null) ? timestamp : new Date();
        this.imagePath = imagePath;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
    }

    // Getters
    public long getPostId() { return postId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public Date getTimestamp() { return timestamp; }
    public String getImagePath() { return imagePath; }
    public long getOwnerId() { return ownerId; }
    public String getOwnerType() { return ownerType; }

    // Setters
    public void setPostId(long postId) { this.postId = postId; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }
    public void setOwnerType(String ownerType) { this.ownerType = ownerType; }

}

