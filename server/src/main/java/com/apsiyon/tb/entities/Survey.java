package com.apsiyon.tb.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Survey extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;


    private String surveyTitle;
    private String surveyDescription;
    private LocalDateTime creationTime;

    @OneToMany(mappedBy = "survey")
    private List<SurveyResponse> responses;
}
