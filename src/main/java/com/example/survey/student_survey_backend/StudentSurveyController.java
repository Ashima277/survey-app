package com.example.survey.student_survey_backend;
import com.example.survey.student_survey_backend.StudentSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "http://localhost:4200") // Allow frontend access
public class StudentSurveyController {

    @Autowired
    private StudentSurveyService surveyService;

    @GetMapping
    public List<StudentSurvey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Optional<StudentSurvey> getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

 

    @PostMapping
    public StudentSurvey createSurvey(@RequestBody StudentSurvey survey) {
      return surveyService.saveSurvey(survey);
      

    }

    @PutMapping("/{id}")
    public StudentSurvey updateSurvey(@PathVariable Long id, @RequestBody StudentSurvey updatedSurvey) {
        return surveyService.updateSurvey(id, updatedSurvey);
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
           surveyService.deleteSurvey(id);
           return ResponseEntity.noContent().build();
}

}