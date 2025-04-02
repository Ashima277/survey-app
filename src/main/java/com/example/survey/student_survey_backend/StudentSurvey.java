package com.example.survey.student_survey_backend;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "student_surveys")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentSurvey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String streetAddress;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private LocalDate dateOfSurvey;

    @ElementCollection(targetClass = LikedMost.class)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<LikedMost> likedMost; // Use List to store multiple enums
    

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private InterestSource interestSource;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RecommendationLikelihood recommendationLikelihood;

    @Column(nullable = false)
    private String comment;

    public enum LikedMost {
        STUDENTS,
        LOCATION,
        CAMPUS,
        ATMOSPHERE,
        DORMS,
        SPORTS
    }

    public enum InterestSource {
        FRIENDS, TELEVISION, INTERNET, OTHER
    }

    public enum RecommendationLikelihood {
        VERY_LIKELY, LIKELY, UNLIKELY
    }

    // Other getters and setters remain the same
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getStreetAddress() { return streetAddress; }
    public void setStreetAddress(String streetAddress) { this.streetAddress = streetAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getDateOfSurvey() { return dateOfSurvey; }
    public void setDateOfSurvey(LocalDate dateOfSurvey) { this.dateOfSurvey = dateOfSurvey; }

    public void setInterestSource(String interestSource) {
        this.interestSource = InterestSource.valueOf(interestSource.toUpperCase());
    }
    
    public String getInterestSource() {
        return interestSource.name(); 
    }
    

    public String getRecommendationLikelihood() {
        return this.recommendationLikelihood.name();
    }
    

    public void setRecommendationLikelihood(String recommendationLikelihood) {
        this.recommendationLikelihood = RecommendationLikelihood.valueOf(recommendationLikelihood.toUpperCase());
    }
    
    public List<LikedMost> getLikedMost() {
        return likedMost;
    }

    // Setter updated to accept List<LikedMost>
    public void setLikedMost(List<LikedMost> likedMost) {
        this.likedMost = likedMost;
    }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    
    
}
