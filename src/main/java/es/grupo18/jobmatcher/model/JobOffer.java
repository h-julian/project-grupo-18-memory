package es.grupo18.jobmatcher.model;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class JobOffer {
    private long offerId;
    private String title;
    private String description;
    private Integer requiredExperience;
    private List<String> requiredDegreesList;
    private List<String> requiredSkillsList;
    private Integer salary;
    private long ownerId;
    private Map<String, User> favoriteByUsersMap;


    public JobOffer(long offerId, String title, String description, Integer requiredExperience, List<String> requiredDegreesList, List<String> requiredSkillsList, Integer salary, long ownerId, Map<String, User> favoriteByUsersMap) {
        this.offerId = offerId;
        this.title = title;
        this.description = description;
        this.requiredExperience = requiredExperience;
        this.requiredDegreesList = requiredDegreesList;
        this.requiredSkillsList = requiredSkillsList;
        this.salary = salary;
        this.ownerId = ownerId;
        this.favoriteByUsersMap = new HashMap<>();


    }

    // Getters
    public long getId() { return offerId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getRequiredExperience() { return requiredExperience; }
    public List<String> getRequiredDegrees() { return requiredDegreesList; }
    public List<String> getRequiredSkills() { return requiredSkillsList; }
    public Integer getSalary() { return salary; }
    public long getOwnerId() { return ownerId; }
    public Map<String, User> getFavoriteByUsers() { return favoriteByUsersMap; }

    // Setters
    public void setId(long offerId) { this.offerId = offerId; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setRequiredExperience(Integer requiredExperience) { this.requiredExperience = requiredExperience; }
    public void setRequiredDegrees(List<String> requiredDegreesList) { this.requiredDegreesList = requiredDegreesList; }
    public void setRequiredSkills(List<String> requiredSkillsList) { this.requiredSkillsList = requiredSkillsList; }
    public void setSalary(Integer salary) { this.salary = salary; }
    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }
    public void setFavoriteByUsers(Map<String, User> favoriteByUsersMap) { this.favoriteByUsersMap = favoriteByUsersMap; }
}
