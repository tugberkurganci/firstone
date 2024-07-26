package com.apsiyon.tb.entities;

import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AppUser {


    private String username;
    private String fullName;



    @OneToMany(mappedBy = "user")
    private List<Taxi> taxiRequests;



    @OneToMany(mappedBy = "user")
    private List<Vehicle> vehicles;


    @OneToMany(mappedBy = "creator")
    private List<Survey> createdSurveys;

    @OneToMany(mappedBy = "creator")
    private List<SurveyResponse> surveyResponses;
    @ManyToMany(mappedBy = "users")
    private List<UserEvent> userEvents;

}

