package es.grupo18.jobmatcher.model;

public class Post {
    
    private long postId; 
    private String title;
    private String content;
    private String timestamp; 
    private String imagePath;
    private Account owner;

    // Constructor
    
    public Post() {}

    public Post(long postId, String title, String content, String timestamp, String imagePath, Account owner) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.timestamp = timestamp;
        this.imagePath = imagePath;
        this.owner = owner;
    }

    // Getters

    public long getPostId() { return postId; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public String getTimestamp() { return timestamp; }
    public String getImagePath() { return imagePath; }
    public Account getOwner() { return owner; }

    // Setters

    public void setPostId(long postId) { this.postId = postId; }
    public void setTitle(String title) { this.title = title; }
    public void setContent(String content) { this.content = content; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
    public void setOwner(Account owner) {this.owner = owner; }

}
