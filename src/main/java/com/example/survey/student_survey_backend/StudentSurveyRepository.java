package com.example.survey.student_survey_backend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSurveyRepository extends JpaRepository<StudentSurvey, Long> {
   
    

}
