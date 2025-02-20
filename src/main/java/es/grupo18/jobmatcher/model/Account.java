package es.grupo18.jobmatcher.model;

public abstract class Account {
    private Long id;
    private String name;
    private String email;
    private String password;

    public Account(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    // Update methods
    public void updateName(String newName){
        this.name = newName;
    }

    public void updateEmail(String newEmail){
        this.email = newEmail;
    }

    public void updatePassword(String newPassword){
        this.password = newPassword;
    }

    public static Long generateNewId(){
        return (long) (Math.random() * 1000);
    }
}