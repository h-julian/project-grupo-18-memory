package es.grupo18.jobmatcher.model;

public abstract class Account {
    private long accountId;
    private String name;
    private String email;
    private String password;

    // Constructors

    public Account() {
        this.accountId = generateNewAccountId();
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public Account(long accountId, String name, String email, String password) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters
    public long getAccountId() { return accountId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // Setters
    public void setAccountId(long accountId) { this.accountId = accountId; }
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

    public static long generateNewAccountId(){
        return (long) (Math.random() * 1000);
    }

    // toString
    @Override

    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + 
                ", email='" + email + 
                ", password='" + password + 
                '}';
    }
}