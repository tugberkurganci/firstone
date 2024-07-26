package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.SurveyRequestDto;
import com.apsiyon.tb.dto.SurveyResponseDto;
import com.apsiyon.tb.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyResponseDto> createSurvey(@RequestBody SurveyRequestDto surveyRequestDto) {
        SurveyResponseDto createdSurvey = surveyService.createSurvey(surveyRequestDto);
        return ResponseEntity.ok(createdSurvey);
    }

    @GetMapping
    public ResponseEntity<List<SurveyResponseDto>> getAllSurveys() {
        List<SurveyResponseDto> surveys = surveyService.getAllSurveys();
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SurveyResponseDto> getSurveyById(@PathVariable Long id) {
        Optional<SurveyResponseDto> survey = surveyService.getSurveyById(id);
        return survey.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
        return ResponseEntity.noContent().build();
    }
}
