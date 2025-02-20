package es.grupo18.jobmatcher.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class JobOffer {
    private long offerId;
    private String title;
    private String description;
    private Integer requiredExperience;
    private List<String> requiredDegreeList;
    private List<String> requiredSkillsList;
    private Integer salary;
    private long ownerId;
    private Map<String, User> favoriteByUsersMap;
    private Long companyId;
    private String companyName;

    // Add default constructor
    public JobOffer() {
        // Empty constructor
    }

    public JobOffer(long offerId, String title, String description, Integer requiredExperience, List<String> requiredDegreeList, List<String> requiredSkillsList, Integer salary, long ownerId, Map<String, User> favoriteByUsersMap) {
        this.offerId = offerId;
        this.title = title;
        this.description = description;
        this.requiredExperience = requiredExperience;
        this.requiredDegreeList = requiredDegreeList;
        this.requiredSkillsList = requiredSkillsList;
        this.salary = salary;
        this.ownerId = ownerId;
        this.favoriteByUsersMap = new HashMap<>();
    }

    // Add constructor with parameters
    public JobOffer(Long companyId, String companyName, String description) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.description = description;
    }

    // Getters
    public long getOfferId() { return offerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getRequiredExperience() { return requiredExperience; }
    public List<String> getRequiredDegree() { return requiredDegreeList; }
    public List<String> getRequiredSkills() { return requiredSkillsList; }
    public Integer getSalary() { return salary; }
    public long getOwnerId() { return ownerId; }
    public Map<String, User> getFavoriteByUsers() { return favoriteByUsersMap; }
    public Long getCompanyId() { return companyId; }
    public String getCompanyName() { return companyName; }

    // Setters
    public void setOfferId(long offerId) { this.offerId = offerId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setRequiredExperience(Integer requiredExperience) { this.requiredExperience = requiredExperience; }
    public void setRequiredDegree(List<String> requiredDegreeList) { this.requiredDegreeList = requiredDegreeList; }
    public void setRequiredSkills(List<String> requiredSkillsList) { this.requiredSkillsList = requiredSkillsList; }
    public void setSalary(Integer salary) { this.salary = salary; }
    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }
    public void setFavoriteByUsers(Map<String, User> favoriteByUsersMap) { this.favoriteByUsersMap = favoriteByUsersMap; }
    public void setCompanyId(Long companyId) { this.companyId = companyId; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    // Update methods
    public void updateTitle(String newTitle){
        this.title = newTitle;
    }

    public void updateDescription(String newDescription){
        this.description = newDescription;
    }

    public void updateRequiredExperience(Integer newRequiredExperience){
        this.requiredExperience = newRequiredExperience;
    }

    public void updateSalary(Integer newSalary){
        this.salary = newSalary;
    }

    //Add & Remove methods
    public void addFavoriteUser(User user){
        this.favoriteByUsersMap.put(String.valueOf(user.getId()), user);
    }

    public void removeFavoriteUser(User user){
        this.favoriteByUsersMap.remove(String.valueOf(user.getId()));
    }

    // toString
    @Override
    public String toString() {
        return "JobOffer{id=" + offerId + ", title=" + title + ", description=" + description + "}";
    }

}
