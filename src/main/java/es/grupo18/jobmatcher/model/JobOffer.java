package es.grupo18.jobmatcher.model;

import java.util.List;

public class JobOffer {
    private String id;
    private String title;
    private String description;
    private Integer requiredExperience;
    private List<String> requiredDegreesList;
    private List<String> requiredSkillsList;
    private Integer salary;

    public JobOffer(String id, String title, String description, Integer requiredExperience, List<String> requiredDegreesList, List<String> requiredSkillsList, Integer salary) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredExperience = requiredExperience;
        this.requiredDegreesList = requiredDegreesList;
        this.requiredSkillsList = requiredSkillsList;
        this.salary = salary;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Integer getRequiredExperience() { return requiredExperience; }
    public List<String> getRequiredDegrees() { return requiredDegreesList; }
    public List<String> getRequiredSkills() { return requiredSkillsList; }
    public Integer getSalary() { return salary; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setRequiredExperience(Integer requiredExperience) { this.requiredExperience = requiredExperience; }
    public void setRequiredDegrees(List<String> requiredDegreesList) { this.requiredDegreesList = requiredDegreesList; }
    public void setRequiredSkills(List<String> requiredSkillsList) { this.requiredSkillsList = requiredSkillsList; }
    public void setSalary(Integer salary) { this.salary = salary; }
}
