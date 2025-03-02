package es.grupo18.jobmatcher.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "favouriteUsers" })

public class Company extends Account {
    private String bio;
    private String location;
    private String imagePath;
    private List<User> favouriteUsersList;

    // Constructors

    public Company() {
    }

    public Company(long accountId, String name, String email, String password, String location, String bio,
            String imagePath, List<User> favouriteUsersList) {
        super(accountId, name, email, password);
        this.location = location;
        this.bio = bio;
        this.imagePath = imagePath;
        this.favouriteUsersList = new ArrayList<>(favouriteUsersList);
    }

    // Getters

    public String getBio() {
        return bio;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getLocation() {
        return location;
    }

    public List<User> getFavoriteUsers() {
        return favouriteUsersList;
    }

    // Setters

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFavoriteUsers(List<User> favouriteUsersList) {
        this.favouriteUsersList = new ArrayList<>(favouriteUsersList);
    }

    // Update methods

    public void updateLocation(String newLocation) {
        this.location = newLocation;
    }

    public void updateBio(String newBio) {
        this.bio = newBio;
    }

    public void updateImagePath(String newImagePath) {
        this.imagePath = newImagePath;
    }

    // Add & Remove methods

    public void addFavouriteUser(User user) {
        this.favouriteUsersList.add(user);
    }

    public void removeFavoriteUser(User user) {
        this.favouriteUsersList.remove(user);
    }

    public void addImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void removeImage() {
        this.imagePath = null;
    }

    // toString

    @Override
    public String toString() {
        return "Company{" + super.toString() +
                "bio='" + bio +
                ", location='" + location +
                ", imagePath='" + imagePath +
                ", favouriteUsersList=" + favouriteUsersList +
                '}';
    }

}
