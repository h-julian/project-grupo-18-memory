package es.grupo18.jobmatcher.model;

import java.util.List;
import java.util.Map;

public class MatchManager {

    public static double calculateMatch(int experience, List<String> degreeList, List<String> skillsList, 
                                        int requiredExperience, List<String> requiredDegrees, List<String> requiredSkills, 
                                        Map<String, Double> weights) {
        double experienceWeight = weights.getOrDefault("experience", 1.0);
        double degreeWeight = weights.getOrDefault("degree", 1.0);
        double skillsWeight = weights.getOrDefault("skills", 1.0);

        double experienceScore = (double) Math.min(experience, requiredExperience) / requiredExperience * experienceWeight;
        double degreeScore = (double) degreeList.stream().filter(requiredDegrees::contains).count() / requiredDegrees.size() * degreeWeight;
        double skillsScore = (double) skillsList.stream().filter(requiredSkills::contains).count() / requiredSkills.size() * skillsWeight;

        double totalWeight = experienceWeight + degreeWeight + skillsWeight;
        double matchScore = (experienceScore + degreeScore + skillsScore) / totalWeight * 100;

        return matchScore;
    }

    public static boolean isMatch(int experience, List<String> degreeList, List<String> skillsList, 
                                  int requiredExperience, List<String> requiredDegrees, List<String> requiredSkills, 
                                  Map<String, Double> weights) {
        return calculateMatch(experience, degreeList, skillsList, requiredExperience, requiredDegrees, requiredSkills, weights) > 50;
    }
}