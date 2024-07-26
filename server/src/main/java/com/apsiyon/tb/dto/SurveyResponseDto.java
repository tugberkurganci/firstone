package com.apsiyon.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDto {
    private Long id;
    private String surveyTitle;
    private String surveyDescription;
    private LocalDateTime creationTime;
    private Long creatorId;
}