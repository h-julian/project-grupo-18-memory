package es.grupo18.jobmatcher.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class ScoreCalculator {
    
    public int calculateScore(Map<String, Integer> answers, boolean isCompany) {
        if (answers == null || answers.isEmpty()) {
            return 0;
        }

        // Get total number of questions
        int totalQuestions = answers.size();
        
        // Calculate maximum possible score (assuming max value per answer is 3)
        int maxPossibleScore = totalQuestions * 3;
        
        // Sum all answer values
        int totalScore = answers.values().stream()
                               .mapToInt(Integer::intValue)
                               .sum();
        
        // Convert to percentage (0-100)
        double percentage = (totalScore * 100.0) / maxPossibleScore;
        
        // Apply any specific weights for company vs user
        if (isCompany) {
            // Add company-specific score adjustments if needed
            percentage = adjustCompanyScore(percentage);
        } else {
            // Add user-specific score adjustments if needed
            percentage = adjustUserScore(percentage);
        }
        
        // Ensure score is between 0 and 100
        return Math.min(100, Math.max(0, (int) Math.round(percentage)));
    }

    private double adjustCompanyScore(double score) {
        // Add company-specific adjustments here if needed
        return score;
    }

    private double adjustUserScore(double score) {
        // Add user-specific adjustments here if needed
        return score;
    }
}
