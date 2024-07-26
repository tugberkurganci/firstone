package com.apsiyon.tb.controller;

import com.apsiyon.tb.dto.SurveyResponseRequestDto;
import com.apsiyon.tb.dto.SurveyResponseResponseDto;
import com.apsiyon.tb.services.SurveyResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/responses")
public class SurveyResponseController {

    @Autowired
    private SurveyResponseService surveyResponseService;

    @PostMapping
    public ResponseEntity<SurveyResponseResponseDto> createSurveyResponse(@RequestBody SurveyResponseRequestDto surveyResponseRequestDto) {
        SurveyResponseResponseDto createdResponse = surveyResponseService.createSurveyResponse(surveyResponseRequestDto);
        return ResponseEntity.ok(createdResponse);
    }

    @GetMapping
    public ResponseEntity<List<SurveyResponseResponseDto>> getAllResponses() {
        List<SurveyResponseResponseDto> responses = surveyResponseService.getAllResponses();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public List<SurveyResponseResponseDto> getResponseBySurveyId(@PathVariable Long id) {
        return surveyResponseService.getResponseById(id);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResponse(@PathVariable Long id) {
        surveyResponseService.deleteResponse(id);
        return ResponseEntity.noContent().build();
    }
}
