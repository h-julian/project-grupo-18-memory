package es.grupo18.jobmatcher.model;

import java.util.List;
import java.util.ArrayList;

public class User extends Account {

    private String phone;
    private String location;
    private String bio;
    private Integer experience;
    private List<String> degreesList;
    private List<String> skillsList;
    private String imagePath;
    private List<Company> favouriteCompaniesList;



    public User() {
    }

    public User(Long accountId, String name, String email, String password, String phone, String location, String bio, Integer experience, List<String> degreesList, List<String> skillsList, String imagePath) {
        super(accountId, name, email, password);
        this.phone = phone;
        this.bio = bio;
        this.location = location;
        this.experience = experience;
        this.degreesList = degreesList;
        this.skillsList = skillsList;
        this.imagePath = imagePath;
    }
 
    public User(Long accountId, String name, String email, String password, String bio, String imagePath) {
        super(accountId, name, email, password);
        this.bio = bio;
        this.imagePath = imagePath;
        this.skillsList = new ArrayList<>();
        this.degreesList = new ArrayList<>();
        this.favouriteCompaniesList = new ArrayList<>();
        this.experience = 0;
    }

    // Getters
    public String getPhone() {return phone;}
    public String getLocation() {return location;}
    public String getBio() {return bio;}
    public Integer getExperience() {return experience;}
    public List<String> getDegrees() {return degreesList;}
    public List<String> getSkills() {return skillsList;}
    public String getImagePath() {return imagePath;}
    public List<User> getfavouriteCompaniesList() { return getfavouriteCompaniesList(); }


    // Setters
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public void setDegrees(List<String> degreesList) {
        this.degreesList = degreesList;
    }

    public void setSkills(List<String> skillsList) {
        this.skillsList = skillsList;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    // Update methods

    public void updatePhone(String newPhone) {
        this.phone = newPhone;
    }

    public void updateLocation(String newLocation) {
        this.location = newLocation;
    }

    public void updateBio(String newBio) {
        this.bio = newBio;
    }

    public void updateExperience(Integer newExperience) {
        this.experience = newExperience;
    }

    public void updateDegrees(List<String> newDegrees) {
        this.degreesList = newDegrees;
    }

    public void updateSkills(List<String> newSkills) {
        this.skillsList = newSkills;
    }

    public void updateImagePath(String newImagePath) {
        this.imagePath = newImagePath;
    }

    // Add and remove methods

    public void addImage(String imagePath) {
        this.imagePath = imagePath;
    }

    public void removeImage() {
        this.imagePath = null;
    }

    public void addFavouriteCompaniesList(Company company){
        this.favouriteCompaniesList.add(company);
    }

    public void removeFavoriteUser(Company company){
        this.favouriteCompaniesList.remove(company);
    }


}
