package com.apsiyon.tb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseResponseDto {
    private Long id;
    private Long surveyId;
    private Long userId;
    private String comment;
    private boolean response;
}