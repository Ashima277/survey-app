package com.example.survey.student_survey_backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentSurveyService {

    @Autowired
    private StudentSurveyRepository surveyRepository;

    // Get all surveys
    public List<StudentSurvey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    // Get a survey by its ID
    public Optional<StudentSurvey> getSurveyById(Long id) {
        return surveyRepository.findById(id);
    }

    // Create a new survey
    public StudentSurvey createSurvey(StudentSurvey survey) {
        return surveyRepository.save(survey);
    }

    // Update an existing survey
    public StudentSurvey updateSurvey(Long id, StudentSurvey updatedSurvey) {
        return surveyRepository.findById(id).map(survey -> {
            // Update regular fields
            survey.setFirstName(updatedSurvey.getFirstName());
            survey.setLastName(updatedSurvey.getLastName());
            survey.setStreetAddress(updatedSurvey.getStreetAddress());
            survey.setCity(updatedSurvey.getCity());
            survey.setState(updatedSurvey.getState());
            survey.setZip(updatedSurvey.getZip());
            survey.setTelephone(updatedSurvey.getTelephone());
            survey.setEmail(updatedSurvey.getEmail());
            survey.setDateOfSurvey(updatedSurvey.getDateOfSurvey());
    
            // Handle List<LikedMost> safely
            if (updatedSurvey.getLikedMost() != null) {
                survey.getLikedMost().clear();  // Clear existing list
                survey.getLikedMost().addAll(updatedSurvey.getLikedMost()); // Add updated items
            }
    
            // Update other fields
            survey.setInterestSource(updatedSurvey.getInterestSource());
            survey.setRecommendationLikelihood(updatedSurvey.getRecommendationLikelihood());
            survey.setComment(updatedSurvey.getComment());
    
            return surveyRepository.save(survey);
        }).orElseThrow(() -> new RuntimeException("Survey not found with ID: " + id));

    }
    
    // Delete a survey by its ID
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    // Save a new survey or update an existing one
    public StudentSurvey saveSurvey(StudentSurvey survey) {
        return surveyRepository.save(survey);
    }
}
